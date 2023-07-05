package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address")
    val address: String,
    @SerializedName("blockoutsDates")
    val blockoutsDates: List<String>,
    @SerializedName("city")
    val city: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("eventShortDescription")
    val eventShortDescription: String,
    @SerializedName("eventType")
    val eventType: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("gender")
    val gender: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("locationDesc")
    val locationDesc: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("participation")
    val participation: Participation,
    @SerializedName("registered")
    val registered: Boolean,
    @SerializedName("standardPrice")
    val standardPrice: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("state")
    val state: String
)