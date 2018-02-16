package com.troncodroide.themoviedb.business

import com.troncodroide.themoviedb.rest.SearchMoviesProvider
import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable

open class TheMoviesUseCase {

    private val api_key = "93aea0c77bc168d8bbce3918cefefa45"

    open fun getPopularMovies(page: String): Observable<Model.MovieResult> {
        return SearchMoviesProvider.provideRepository().getPopularMovies(page, "en_ES", "")
    }

    open fun searchMovies(page: String): Observable<Model.MovieResult> {
        return SearchMoviesProvider.provideRepository().searchMovies(page)
    }

    open fun getGenre(): Observable<Model.GenreResult> {
        return SearchMoviesProvider.provideRepository().getGenres()
    }

}