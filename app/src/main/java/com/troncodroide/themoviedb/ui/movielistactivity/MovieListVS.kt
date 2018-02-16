package com.troncodroide.themoviedb.ui.movielistactivity

import com.troncodroide.themoviedb.rest.models.Model
import java.io.Serializable

data class MovieListVS(val data: MutableList<Model.Movie> = mutableListOf(), val genres: MutableList<Model.Genre> = mutableListOf(), val currentPage: Int = 0, val query: String? = null) : Serializable