package com.troncodroide.themoviedb.rest

import com.troncodroide.themoviedb.rest.models.Model.Result
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface TheMovieDBService {

    @GET("/list/1")
    fun getMovies(@Query("sort_by") sortby: String,
                  @Query("page") page: String): Observable<Result>


    companion object {
        fun create(): TheMovieDBService {
            val httpClient = OkHttpClient().newBuilder()
            val interceptor = Interceptor { chain ->
                val request = chain?.request()?.newBuilder()
                        ?.addHeader("Authorization", "Bearer 93aea0c77bc168d8bbce3918cefefa45")
                        ?.addHeader("Content-Type", "application/json;charset=utf-8")
                        ?.build()
                chain?.proceed(request)
            }
            httpClient.networkInterceptors().add(interceptor)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/4/")
                    .build()
            return retrofit.create(TheMovieDBService::class.java)
        }
    }
}