package com.beststreaming.model.repository

import com.beststreaming.model.data.ApiHelper
import com.beststreaming.model.data.ApiService

/**
 * ApiHelper Interface Implementation
 * @property ApiHelper injecting ApiHelperImpl.
 */
class RemoteRepository(private val apiHelper: ApiHelper) {
    suspend fun getMoviesRemote() = apiHelper.getMovies()
}