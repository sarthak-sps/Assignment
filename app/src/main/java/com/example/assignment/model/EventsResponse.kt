package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class EventsResponse(
    @SerializedName("data")
    val data: Data? = null,
    @SerializedName("status")
    val status: Boolean? = null,
    @SerializedName("statusCode")
    val statusCode: Int? = null,
    @SerializedName("statusMessage")
    val statusMessage: String ="",
    @SerializedName("totalRecords")
    val totalRecords: Int? = null
)

data class FilterPreferences(val filterPreferences : List<FilterPreference?>)