package com.troncodroide.themoviedb.business

import android.util.Log
import com.troncodroide.themoviedb.rest.SearchMoviesProvider
import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class TheMoviesUseCase {

    open fun getMovies(page: String): Observable<Model.Result> {
        return SearchMoviesProvider.provideRepository().searchMovies(page)
    }

}