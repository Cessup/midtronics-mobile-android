package com.cessup.midtronics.data

import com.cessup.midtronics.data.entities.CountryEntity
import com.cessup.midtronics.data.source.remote.CountriesServices
import com.cessup.midtronics.data.source.remote.CountriesXml
import com.cessup.midtronics.domain.model.Country
import com.cessup.midtronics.domain.repositories.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.xml.parsers.DocumentBuilderFactory

class CountriesRepositoryImpl(
    val countriesXml: CountriesXml,
    val countriesServices: CountriesServices
) : CountriesRepository{

    override suspend fun getCountryDetails(countryName: String): Flow<Result<List<Country>>> = flow {
        try {
            val response = countriesServices.getCountryByName(countryName)
            if(response.isSuccessful){
                val result = response.body() ?: emptyList()
                val listEntity: List<CountryEntity> = result.toList()
                val list: List<Country> = listEntity.map { it->
                    Country(
                        it.name,
                        it.capital,
                        it.area,
                        it.region,
                        it.subregion,
                        it.population,
                        it.flags
                    )
                }

                emit(Result.success(list))
            }else{
                emit(Result.failure(NullPointerException()))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getCountries(): Flow<Result<List<String>>> = flow {
        try {
            val response = countriesXml.getCountries()
            if(response.isSuccessful){
                val result = parseCountriesXml(response.body()!!)
                emit(Result.success(result))
            }else{
                emit(Result.failure(NullPointerException()))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun parseCountriesXml(xml: String): List<String> {
        val countries = mutableListOf<String>()
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val inputStream = xml.byteInputStream()
        val document = builder.parse(inputStream)
        val items = document.getElementsByTagName("item")

        for (i in 0 until items.length) {
            countries.add(items.item(i).textContent)
        }
        return countries
    }
}