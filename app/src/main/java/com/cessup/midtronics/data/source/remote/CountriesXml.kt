package com.cessup.midtronics.data.source.remote

import retrofit2.Response
import retrofit2.http.GET

interface CountriesXml {
    @GET("vinaygaba/Ultimate-String-Array-List/master/Countries.xml")
    suspend fun getCountries(): Response<String>
}