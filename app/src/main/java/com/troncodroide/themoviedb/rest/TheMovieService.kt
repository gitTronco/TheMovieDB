package com.troncodroide.themoviedb.rest

import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface TheMovieDBService {

    @GET("4/list/1")
    fun getMovies(@Query("sort_by") sortby: String,
                  @Query("api_key") api_key: String,
                  @Query("page") page: String): Observable<Model.MovieResult>

    @GET("3/genre/movie/list")
    fun getGenders(@Query("api_key") api_key: String): Observable<Model.GenreResult>

    @GET("3/movie/popular")
    fun getPopularMovies(
            @Query("language") lan: String?,
            @Query("region") region: String?,
            @Query("api_key") api_key: String,
            @Query("page") page: String?): Observable<Model.MovieResult>

    companion object {
        fun create(): TheMovieDBService {
            val httpClient = OkHttpClient().newBuilder()
            val headerInterceptor = Interceptor { chain ->
                val request = chain?.request()?.newBuilder()
                        ?.addHeader("Content-Type", "application/json;charset=utf-8")
                        ?.build()
                chain?.proceed(request)
            }
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.networkInterceptors().add(headerInterceptor)
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/")
                    .build()
            return retrofit.create(TheMovieDBService::class.java)
        }
    }
}