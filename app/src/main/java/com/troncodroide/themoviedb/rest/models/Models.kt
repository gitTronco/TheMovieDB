package com.troncodroide.themoviedb.rest.models

import java.io.Serializable

object Model {
    data class Movie(
            val title: String,
            val year: String,
            val url: String,
            val html_url: String,
            val followers_url: String,
            val following_url: String,
            val starred_url: String,
            val gists_url: String,
            val type: String,
            val score: Int
    )

    data class Result(val total_count: Int, val incomplete_results: Boolean, val items: List<Movie>)
}
