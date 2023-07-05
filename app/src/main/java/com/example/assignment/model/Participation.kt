package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class Participation(
    @SerializedName("boysMax")
    val boysMax: String,
    @SerializedName("boysMin")
    val boysMin: String,
    @SerializedName("girlsMax")
    val girlsMax: String,
    @SerializedName("girlsMin")
    val girlsMin: String,
    @SerializedName("maxRange")
    val maxRange: String,
    @SerializedName("minRange")
    val minRange: String,
    @SerializedName("type")
    val type: String
)