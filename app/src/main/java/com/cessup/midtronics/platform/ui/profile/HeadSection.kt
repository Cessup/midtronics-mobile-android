package com.cessup.midtronics.platform.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cessup.midtronics.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileSection(title:String,
                   onNavProfile: () -> Unit) {

    val viewModel: HeadViewModel = koinViewModel()
    val picture by viewModel.picture.collectAsState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,18.dp)
            .clickable{onNavProfile()},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(24.dp)
        )

        Box (modifier = Modifier
            .padding(24.dp)){
            AsyncImage(
                model = picture,
                contentDescription = title,
                placeholder = painterResource(R.drawable.ic_image_24),
                error = painterResource(R.drawable.ic_hide_image_24),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
        }


    }
}