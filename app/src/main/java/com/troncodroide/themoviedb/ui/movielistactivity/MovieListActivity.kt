package com.troncodroide.themoviedb.ui.movielistactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.troncodroide.themoviedb.R
import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.subjects.PublishSubject

class MovieListActivity : AppCompatActivity(), MovieListV {
    override fun render(viexState: MovieListVS) {
        renderList(viexState.data)
    }

    private fun renderList(data: List<Model.Movie>) {

    }

    var queryIntent: PublishSubject<String> = PublishSubject.create()
    var pageNext: PublishSubject<String> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
    }

    override fun queryIntent(): PublishSubject<String> {
        return queryIntent
    }

    override fun pageNextRequest(): PublishSubject<String> {
        return pageNext
    }

}
