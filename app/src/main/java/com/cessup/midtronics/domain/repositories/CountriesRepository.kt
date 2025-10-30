package com.cessup.midtronics.domain.repositories

import com.cessup.midtronics.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getCountries(): Flow<Result<List<String>>>
    suspend fun getCountryDetails(countryName: String): Flow<Result<List<Country>>>
}