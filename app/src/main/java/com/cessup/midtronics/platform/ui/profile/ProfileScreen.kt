package com.cessup.midtronics.platform.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cessup.midtronics.R
import com.cessup.midtronics.domain.model.Resume
import com.cessup.midtronics.domain.model.School
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.Work
import com.cessup.midtronics.platform.ui.UiState
import org.koin.androidx.compose.koinViewModel


/**
 * That is the Profile contain all information about user
 *
 * This class contain the start flow
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Composable
fun ProfileScreen(
    onNavNetworkError: (String) -> Unit) {

    val viewModel: ProfileViewModel = koinViewModel()
    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        when (state) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.NetworkError -> onNavNetworkError((state as UiState.NetworkError).message)
            is UiState.Success -> ProfileContent((state as UiState.Success).data as Resume)
            is UiState.Error -> Toast.makeText(context,(state as UiState.Error).message, Toast.LENGTH_LONG).show()
        }
    }
}


@Composable
fun ProfileContent(resume: Resume) {
    val user: User = resume.user
    val schools: List<School> = resume.schools
    val works: List<Work> = resume.works

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            ProfileHead(
                "${user.details.name} ${user.details.lastname}",
                "${user.details.address} \n${user.email} \n${user.phone}",
                user.details.picture ?: ""
            )
        }

        item {
            Text(
                text = "School",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(0.dp,24.dp),
            )
        }

        items(schools) { school->
            ItemSchool(school)
        }

        item{
            Text(
                text = "Work",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(0.dp,24.dp),
            )
        }

        items(works) { work->
            ItemWork(work)
        }
    }
}

@Composable
fun ProfileHead(name: String, information:String, picture:String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box (modifier = Modifier
            .padding(24.dp)){
            AsyncImage(
                model = picture,
                contentDescription = name,
                placeholder = painterResource(R.drawable.ic_image_24),
                error = painterResource(R.drawable.ic_hide_image_24),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier,
        )

        Spacer(modifier = Modifier.padding(0.dp,12.dp))

        Text(
            text = information,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier,
        )
    }
}

@Composable
fun ItemSchool(school: School) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = school.picture,
                contentDescription = school.name,
                placeholder = painterResource(R.drawable.ic_image_24),
                error = painterResource(R.drawable.ic_hide_image_24),
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Column {
                Text(
                    text = school.studies,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = school.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                )
            }


        }
    }

}

@Composable
fun ItemWork(work: Work) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = work.picture,
                contentDescription = work.position,
                placeholder = painterResource(R.drawable.ic_image_24),
                error = painterResource(R.drawable.ic_hide_image_24),
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Column {
                Text(
                    text = work.position,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = work.company,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = work.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                )
            }

        }

    }

}