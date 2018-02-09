package com.troncodroide.themoviedb.rest

import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable

object SearchMoviesProvider {
    fun provideRepository(): SearchRepository {
        return SearchRepository(TheMovieDBService.create())
    }
}

class SearchRepository(private val apiService: TheMovieDBService) {
    fun searchMovies(page: String): Observable<Model.Result> {
        return apiService.getMovies("popularity", page)
    }
}