package com.beststreaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beststreaming.databinding.ActivityMainBinding
import com.beststreaming.model.Movie
import com.beststreaming.utils.Status
import com.beststreaming.view.adapter.MovieAdapter
import com.beststreaming.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MovieViewModel by viewModel()

    private lateinit var adapter: MovieAdapter
    private val movies = arrayListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initObserve()
    }

    private fun initUI() {
        adapter = MovieAdapter(movies)
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMain.adapter = adapter
    }

    private fun initObserve() {
        mainViewModel.movies.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movies -> renderList(movies) }
                    binding.recyclerViewMain.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerViewMain.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    //adapter call
    private fun renderList(movies: List<Movie>) {
        adapter.addData(movies)
        adapter.notifyDataSetChanged()
    }
}