package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class UpdateFilterResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("statusMessage")
    val statusMessage: String,
    @SerializedName("totalRecords")
    val totalRecords: Int
)