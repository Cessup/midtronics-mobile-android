package com.cessup.midtronics.data.entities

data class CountryEntity(
    val name: Name,
    val capital: List<String>?,
    val area: String?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val flags: Flags
)

data class Name(
    val common: String?,
    val official: String?
)

data class Flags(
    val png: String?,
    val svg: String?
)