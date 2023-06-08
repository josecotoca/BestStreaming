package com.beststreaming.model.data

import com.beststreaming.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    /**
     * Request Get From Api
     * @return serialized list in movie.
     */
    @GET("skydoves/176c209dbce4a53c0ff9589e07255f30/raw/6489d9712702e093c4df71500fb822f0d408ef75/DisneyPosters2.json")
    suspend fun getMovies(): Response<List<Movie>>

}
