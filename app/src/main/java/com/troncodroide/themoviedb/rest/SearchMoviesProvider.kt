package com.troncodroide.themoviedb.rest

import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable

object SearchMoviesProvider {
    fun provideRepository(): SearchRepository {
        return SearchRepository(TheMovieDBService.create())
    }
}

class SearchRepository(private val apiService: TheMovieDBService) {
    fun searchMovies(page: String): Observable<Model.MovieResult> {
        return apiService.getMovies(page = page, api_key = "93aea0c77bc168d8bbce3918cefefa45", sortby = "")
    }

    fun getPopularMovies(page: String?, lan: String = "en-US", region: String?): Observable<Model.MovieResult> {
        return apiService.getPopularMovies(page = page, lan = lan, region = region, api_key = "93aea0c77bc168d8bbce3918cefefa45")
    }

    fun getGenres(): Observable<Model.GenreResult> {
        return apiService.getGenders(api_key = "93aea0c77bc168d8bbce3918cefefa45")
    }
}