package com.enesas.tmdbretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesas.tmdbretrofitrxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movieApiService = MovieAPIService
    private val disposable = CompositeDisposable()

    private val recyclerAdapter = RecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = recyclerAdapter



        val totalPages = 10

        disposable.add(
            // Using range to create a sequence of page numbers
            Observable.range(1, totalPages)
                .concatMap { currentPage ->
                    movieApiService.getPopularMovieData(currentPage)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess { result ->

                            println(currentPage)
                            println(result.results)

                            result.results.let {
                                recyclerAdapter.updateMovieList(it) }
                        }
                        .toObservable() // Convert Single to Observable
                }
                .subscribe(
                    { /* onNext */ },
                    { error -> error.printStackTrace() }
                )
        )
    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose() // Dispose the disposable to avoid memory leaks
    }
}