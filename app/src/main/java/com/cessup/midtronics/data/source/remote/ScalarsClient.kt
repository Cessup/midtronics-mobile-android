package com.cessup.midtronics.data.source.remote

import android.content.Context
import com.cessup.midtronics.platform.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * ApiClient is an object got service configurations
 *
 *
 * @author
 *     Cessup
 * @since 1.0
 */

object ScalarsClient {
    private const val BASE_URL = "https://raw.githubusercontent.com/"
    /**
     * This function give a instance of ApiService
     *
     * @return ApiService that is the object with all services.
     */
    fun getInstance(context: Context): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
}