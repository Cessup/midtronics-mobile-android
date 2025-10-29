package com.cessup.midtronics.data.source.remote

import com.cessup.midtronics.data.entities.CountryEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesServices {
    @GET("v3.1/name/{countryName}")
    suspend fun getCountryByName(
        @Path("countryName") countryName: String
    ): Response<List<CountryEntity>>
}