package com.troncodroide.themoviedb.ui.movielistactivity

import android.util.Log
import com.troncodroide.themoviedb.business.TheMoviesUseCase
import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MovieListP(val view: MovieListV, val useCase: TheMoviesUseCase) {

    private var currentState: MovieListVS = MovieListVS()
    private var disposable: Disposable? = null

    private fun getObservables(): Observable<MovieListVS> {
        return Observable.mergeArray(
                getUseCaseObservable(),
                getOnNextPageObserver()
        )
    }

    private fun getUseCaseObservable(): Observable<MovieListVS> {
        return this.useCase.getGenre()
                .zipWith(this.useCase.getPopularMovies(Integer.toString(currentState.currentPage + 1)),
                        BiFunction<Model.GenreResult, Model.MovieResult, MovieListVS> { genre: Model.GenreResult, movie: Model.MovieResult ->
                            if (currentState.currentPage != movie.page) {
                                currentState.data.addAll(movie.results)
                            }
                            currentState.copy(genres = genre.genres, currentPage = movie.page)
                        })
    }

    private fun getOnNextPageObserver(): Observable<MovieListVS> {
        return this.view.pageNextRequest().map { this.currentState.copy() }.concatWith { getUseCaseObservable() }
    }


    private fun getOnQUeryObserver(): Observable<MovieListVS> {
        return this.view.queryIntent().map { query -> this.currentState.copy(query = query) }.concatWith { getUseCaseObservable() }
    }


    fun onPause() {
        this.disposable?.dispose()
    }

    fun onResume() {
        this.disposable = getObservables()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .distinctUntilChanged()
                .subscribe({ vs: MovieListVS ->
                    Log.i("NewState", vs.currentPage.toString())
                    currentState = vs
                    view.render(vs)
                }, { error: Any -> Log.i("Error", error.toString()) })
    }

}
