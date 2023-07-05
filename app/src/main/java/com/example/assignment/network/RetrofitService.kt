package com.example.assignment.network


import com.example.assignment.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

const val BASE_URL = "https://qa.allballapp.com/api/v5/event/"
const val token =
    "Basic eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDFlOTJhYzQ1MTM5OTgyODViMGJmOCIsIm5hbWUiOiJNYW5pc2hhIFdpbGxpYW0iLCJyb2xlIjoiZ2FtZVN0YWZmIiwicGhvbmUiOiIrOTE5ODE1ODQwNzYwIiwiaWF0IjoxNjg3NDIzMjYyLCJleHAiOjE3MTg5NTkyNjJ9.ksqqOm8ypH0uUToG59_kf1QKCWm7OkwKlTqNZ_SNBvc"

interface APIService {
    @GET("getOpportunitiesEvents")
    suspend fun getEvents(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int,
        @Query("teamId") teamId: String,
        @Query("page") page: Int,
        @Query("type") type: String,
        @Query("isFilterOn") isFilterOn: Boolean,
        @Query("filter") filter: String
    ): EventsResponse

    @GET("getFilterPreferences")
    suspend fun getFilterPreferences(
        @Header("Authorization") token: String,
        @Query("type") type: String,
        @Query("isFilterOn") isFilterOn: Boolean
    ): EventsResponse

    @PUT("updateFilterPreferences")
    suspend fun updateFilterPreference(
        @Header("Authorization") token: String,
        @Query("type") type: String,
        @Body body: FilterPreferences
    ): EventsResponse

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
