package com.beststreaming.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beststreaming.model.Movie
import com.beststreaming.model.repository.RemoteRepository
import com.beststreaming.utils.Resource
import kotlinx.coroutines.launch

/**
 * @param Class RemoteRepository
 * @return  Class ViewModel
 */
class MovieViewModel(
    private val mainRepository: RemoteRepository
): ViewModel() {

    /**
     * MutableLiveData, LiveData - Data retention class,
     * with option to be observable in ViewModel
     */
    private val _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies

    init {
        fetchMovies()
    }

    /**
        Retrieve list of movies from repository using viewModel coroutine
     */
    private fun fetchMovies(){
        viewModelScope.launch {
            _movies.postValue(Resource.loading(null))
            mainRepository.getMoviesRemote().let {
                if(it.isSuccessful){
                    _movies.postValue(Resource.success(it.body()))
                } else _movies.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}
