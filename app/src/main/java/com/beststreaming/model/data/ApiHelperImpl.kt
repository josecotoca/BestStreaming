package com.beststreaming.model.data

import com.beststreaming.model.Movie
import retrofit2.Response

/**
 * ApiHelper Interface Implementation
 * @property ApiService of type Retrofit in this case.
 */
class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    /**
     * Call to ApiService Retrofit
     * @return serialized list in movie.
     */
    override suspend fun getMovies(): Response<List<Movie>> = apiService.getMovies()
}