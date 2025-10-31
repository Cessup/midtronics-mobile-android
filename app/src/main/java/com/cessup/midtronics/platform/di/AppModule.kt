package com.cessup.midtronics.platform.di

import com.cessup.midtronics.data.CountriesRepositoryImpl
import com.cessup.midtronics.data.UserRepositoryImpl
import com.cessup.midtronics.data.source.local.db.AppDatabase
import com.cessup.midtronics.data.source.local.temp.LocalStorage
import com.cessup.midtronics.data.source.local.temp.LocalStorageImpl
import com.cessup.midtronics.data.source.remote.ApiClient
import com.cessup.midtronics.data.source.remote.CountriesServices
import com.cessup.midtronics.data.source.remote.CountriesXml
import com.cessup.midtronics.data.source.remote.ScalarsClient
import com.cessup.midtronics.domain.repositories.CountriesRepository
import org.koin.dsl.module
import com.cessup.midtronics.domain.repositories.UserRepository
import com.cessup.midtronics.platform.ui.countries.CountriesViewModel
import com.cessup.midtronics.platform.ui.countries.CountryDetailsViewModel
import com.cessup.midtronics.platform.ui.home.HomeViewModel
import com.cessup.midtronics.platform.ui.profile.HeadViewModel
import com.cessup.midtronics.platform.ui.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel

val appModule = module {

    single<CountriesXml> {
        ScalarsClient.getInstance(androidContext()).create(CountriesXml::class.java)
    }

    single<CountriesServices> {
        ApiClient.getInstance(androidContext()).create(CountriesServices::class.java)
    }

    single {
        AppDatabase.getInstance(androidContext())
    }

    single { get<AppDatabase>().userDao() }

    single<LocalStorage> { LocalStorageImpl(androidContext()) }

    single<UserRepository> { UserRepositoryImpl(get(),get()) }
    single<CountriesRepository> { CountriesRepositoryImpl(get(),get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { CountriesViewModel(get()) }
    viewModel { CountryDetailsViewModel(get()) }
    viewModel { HeadViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
