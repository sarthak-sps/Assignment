package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("filterPreferences")
    val filterPreferences: List<FilterPreference>,
    @SerializedName("result")
    val result: List<Result>
)