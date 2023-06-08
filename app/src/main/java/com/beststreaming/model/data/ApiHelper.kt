package com.beststreaming.model.data

import com.beststreaming.model.Movie
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovies(): Response<List<Movie>>
}