package com.beststreaming.model

import com.squareup.moshi.Json

data class Movie (
    val id: Int,
    val name: String,
    val release: String,
    val playtime: String,
    val description: String,
    val plot: String,
    /**
     * Serializing Json poster response into posterUrl
     */
    @Json(name = "poster")
    val posterUrl: String,
    val gif: String
)

/*
{
    companion object {
        fun fromStoredMovie(storedMovie: StoredMovie): Movie {
            return Animal(
                storedAnimal.id,
                storedAnimal.name,
                storedAnimal.latinName,
                storedAnimal.geoRange,
                storedAnimal.diet,
                storedAnimal.imageUrl
            )
        }

    }

    fun asStoredMovie(): StoredMovie {
        return StoredAnimal.newBuilder()
            .setId(id)
            .setName(name)
            .setLatinName(latinName)
            .setGeoRange(geoRange)
            .setImageUrl(imageUrl)
            .build()
    }

}
 */