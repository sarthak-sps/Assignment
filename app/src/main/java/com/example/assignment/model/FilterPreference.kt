package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class FilterPreference(
    @SerializedName("key")
    val key: String="",
    @SerializedName("name")
    val name: String="",
    @SerializedName("status")
    val status: Int=0,
    @SerializedName("value")
    var value: Int=0
)