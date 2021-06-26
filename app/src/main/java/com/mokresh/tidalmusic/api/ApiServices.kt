package com.mokresh.tidalmusic.api


import android.content.Context
import com.mokresh.tidalmusic.BuildConfig
import com.mokresh.tidalmusic.albums.model.AlbumsResponseBody
import com.mokresh.tidalmusic.artist.models.ArtistsResponseBody
import com.mokresh.tidalmusic.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("search/artist")
    suspend fun getArtist(@Query("q") query: String, @Query("index") index: Int): Response<ArtistsResponseBody>


    @GET("search/album")
    suspend fun getAlbums(@Query("q") query: String, @Query("index") index: Int): Response<AlbumsResponseBody>


    companion object {

        fun create(context: Context): ApiServices {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.connectTimeout(40, TimeUnit.SECONDS).readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
            okHttpClient.addInterceptor(logging)
            okHttpClient.addInterceptor(NetworkConnectionInterceptor(context))


            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServices::class.java)
        }
    }

}