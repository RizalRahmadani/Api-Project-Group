package com.example.api_project_group.network

import com.example.api_project_group.model.Film
import com.example.api_project_group.model.RestponseDataFilmItem
import retrofit2.Call
import retrofit2.http.*


interface RestfulAPIFilm {

    @GET("film")
    fun getAllFilm() : Call<List<RestponseDataFilmItem>>

    @GET("admin/car/{id}")
    fun getDetailFilm(@Path("id") id : Int): Call<List<RestponseDataFilmItem>>

    @DELETE("film/{id}")
    fun deleteFilm(@Path("id") id : Int) : Call<Int>

    @POST("film")
    fun addFilm(@Body film : Film) : Call<RestponseDataFilmItem>

    @PUT("film/{id}")
    fun updateFilm(@Path ("id") id : Int, @Body film : Film) : Call<List<RestponseDataFilmItem>>

}