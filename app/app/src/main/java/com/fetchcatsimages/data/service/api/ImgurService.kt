package com.fetchcatsimages.data.service.api

import com.fetchcatsimages.BuildConfig
import com.fetchcatsimages.data.model.HTTPResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ImgurService {

    @GET(BuildConfig.GET_URL)
    suspend fun getAllImages(): Response<HTTPResponse>

    companion object {
        private fun getAuthorization(): OkHttpClient {
            return OkHttpClient().newBuilder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder().header(
                    "Authorization",
                    "Client-ID " + BuildConfig.CLIENT_ID
                )
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }.build()
        }

        operator fun invoke(): ImgurService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getAuthorization())
                .build()
                .create(ImgurService::class.java)
        }
    }
}