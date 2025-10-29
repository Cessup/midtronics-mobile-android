package com.cessup.midtronics.platform.di

import androidx.room.Room
import com.cessup.midtronics.data.CountriesRepositoryImpl
import com.cessup.midtronics.data.UserRepositoryImpl
import com.cessup.midtronics.data.source.local.db.AppDatabase
import com.cessup.midtronics.data.source.remote.ApiClient
import com.cessup.midtronics.data.source.remote.CountriesServices
import com.cessup.midtronics.domain.repositories.CountriesRepository
import org.koin.dsl.module
import com.cessup.midtronics.domain.repositories.UserRepository
import com.cessup.midtronics.platform.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import retrofit2.Retrofit

val appModule = module {

    single { ApiClient }

    single<CountriesServices> {
        get<Retrofit>().create(CountriesServices::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "user_db"
        ).build()
    }
    single { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl() }
    single<CountriesRepository> { CountriesRepositoryImpl() }
    viewModel { HomeViewModel(get()) }

}
