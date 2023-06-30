package com.rehan.radiusandroidassignment.api

import com.rehan.radiusandroidassignment.models.Facilities
import retrofit2.Response
import retrofit2.http.GET

interface FacilitiesAPI {

    @GET("/iranjith4/ad-assignment/db")
    suspend fun getFacilities(): Response<Facilities>
}