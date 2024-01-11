package com.enesas.tmdbretrofitrxjava

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("trending/movie/day?language=tr-1974")
    fun getPopularMovies(@Query("api_key") api_key : String, @Query("page") page : Int) : Single<PopularMovies>
}