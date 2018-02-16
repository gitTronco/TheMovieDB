package com.troncodroide.themoviedb.rest.models

import java.io.Serializable

object Model {
    data class Movie(
            val video: Boolean,
            val title: String,
            val poster_path: String,
            val original_language: String,
            val original_title: String,
            val popularity: Float,
            val genre_ids: List<Int>,
            val vote_count: Int,
            val vote_average: Float,
            val id: Int,
            val backdrop_path: String,
            val adult: Boolean,
            val overview: String,
            val release_date: String

    )

    data class MovieResult(val page: Int, val total_results: Int, val total_pages: Int, val results: MutableList<Movie>)

    data class Genre(val id: Int, val name: String)

    data class GenreResult(val genres: MutableList<Genre>)
}

