package com.troncodroide.themoviedb.ui.movielistactivity

import io.reactivex.subjects.PublishSubject


interface MovieListV {
    fun queryIntent(): PublishSubject<String>
    fun pageNextRequest(): PublishSubject<String>
    fun render(viexState: MovieListVS)
}