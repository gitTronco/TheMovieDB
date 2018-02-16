package com.troncodroide.themoviedb.ui.movielistactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import com.troncodroide.themoviedb.R
import com.troncodroide.themoviedb.business.TheMoviesUseCase
import com.troncodroide.themoviedb.rest.models.Model
import io.reactivex.subjects.PublishSubject

class MovieListActivity : AppCompatActivity(), MovieListV {

    private lateinit var presenter: MovieListP
    var queryIntent: PublishSubject<String> = PublishSubject.create()
    var pageNext: PublishSubject<String> = PublishSubject.create()
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        getViews()
        createPresenter()
    }

    private fun getViews() {
        recycler = findViewById(R.id.recycler)
    }

    private fun createPresenter() {
        this.presenter = MovieListP(this, TheMoviesUseCase())
    }

    override fun onResume() {
        super.onResume()
        this.presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.onPause()
    }

    override fun queryIntent(): PublishSubject<String> {
        return queryIntent
    }

    override fun pageNextRequest(): PublishSubject<String> {
        return pageNext
    }

    override fun render(viexState: MovieListVS) {
        renderList(viexState)
    }

    private fun renderList(data: MovieListVS) {
        if (recycler.adapter == null) {
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.onScrollToEnd(onScrollNearEnd = { pageNext.onNext("") })
            recycler.adapter = MyAdapter(data)
        } else {
            (recycler.adapter as MyAdapter).setUpdate(data)
        }
    }


    fun RecyclerView.onScrollToEnd(onScrollNearEnd: (Unit) -> Unit)
            = addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            var linearLayoutManager = layoutManager as LinearLayoutManager
            if (linearLayoutManager.childCount + linearLayoutManager.findFirstVisibleItemPosition()
                    >= linearLayoutManager.itemCount - 5) {
                onScrollNearEnd(Unit)
            }
        }
    })
}
