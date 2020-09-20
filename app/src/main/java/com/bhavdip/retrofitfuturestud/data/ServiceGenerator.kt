package com.bhavdip.retrofitfuturestud.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {

        // Base Url
        private const val BASE_URL = "https://api.github.com/"

        // Builder for retrofit
        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        // Retrofit object
        private var retrofit = builder.build()

        // HttpLoggingInterceptor for looging
        private val logging: HttpLoggingInterceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val httpClient = OkHttpClient.Builder()

        fun <S> createService(
            serviceClass: Class<S>?
        ): S {
            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(serviceClass)
        }

        /* // We will use in future when Integrate Hawk Authentication
         fun <S> createService(
             serviceClass: Class<S>?, credentials: HawkCredentials?
         ): S {
             if (credentials != null) {
                 val interceptor =
                     HawkAuthenticationInterceptor(credentials)
                 if (!httpClient.interceptors().contains(interceptor)) {
                     httpClient.addInterceptor(interceptor)
                     builder.client(httpClient.build())
                     retrofit = builder.build()
                 }
             }
             return retrofit.create(serviceClass)
         }*/
    }
}