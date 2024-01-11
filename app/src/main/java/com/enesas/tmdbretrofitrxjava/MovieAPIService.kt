package com.enesas.tmdbretrofitrxjava

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieAPIService {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getPopularMovieData(page : Int): Single<PopularMovies> {
        return api.getPopularMovies("Buraya api_key gelecek.", page)
    }
}