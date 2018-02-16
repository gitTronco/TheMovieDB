package com.troncodroide.themoviedb.ui.movielistactivity

import android.support.v4.content.ContextCompat
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.troncodroide.themoviedb.R
import com.troncodroide.themoviedb.rest.models.Model
import com.troncodroide.themoviedb.ui.uitls.loadUrl
import com.troncodroide.themoviedb.ui.uitls.setCompactColor
import com.troncodroide.themoviedb.ui.uitls.setMargin
import com.troncodroide.themoviedb.ui.uitls.setPadding
import java.util.function.Consumer

class MyAdapter(var movieListVS: MovieListVS) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    fun setUpdate(data: MovieListVS) {
        movieListVS = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movieListVS.data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(movieListVS.data[position], movieListVS.genres)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var year: TextView? = null
        var overView: TextView? = null
        var image: ImageView? = null
        var flow: LinearLayout? = null

        fun bind(movie: Model.Movie, genre: List<Model.Genre>) {
            title = itemView.findViewById(R.id.title)
            year = itemView.findViewById(R.id.year)
            overView = itemView.findViewById(R.id.overview)
            image = itemView.findViewById(R.id.picture)
            flow = itemView.findViewById(R.id.flow_tags)

            for (g in movie.genre_ids) {
                flow?.addView(getTagView(g, genre))
            }
            title?.text = movie.original_title
            year?.text = movie.release_date
            overView?.text = movie.overview
            image?.loadUrl(movie.poster_path)
        }

        private fun getTagView(genre_id: Int?, genre: List<Model.Genre>): View? {
            var genremodel = genre.filter { t -> t.id == genre_id }.first()
            var tv = TextView(itemView.context)
            tv.text = genremodel.name
            tv.setBackgroundResource(R.drawable.ic_shape_tag)
            tv.setPadding(5)
            tv.setMargin(10)
            return tv
        }
    }
}