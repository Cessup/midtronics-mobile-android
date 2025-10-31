package com.cessup.midtronics.domain.model

import com.cessup.midtronics.data.entities.Flags
import com.cessup.midtronics.data.entities.Name

/**
 * Country is a entity in this system
 *
 * @param name is the name of the country
 *
 * @author
 *     Cessup
 * @since 1.0
 */
data class Country (
    val name: Name,
    val capital: List<String>?,
    val area: String?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val flags: Flags
)