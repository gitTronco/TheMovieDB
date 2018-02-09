package com.troncodroide.themoviedb.ui.movielistactivity

import com.troncodroide.themoviedb.rest.models.Model
import java.io.Serializable

data class MovieListVS(val data: List<Model.Movie>, val currentPage: Int) : Serializable