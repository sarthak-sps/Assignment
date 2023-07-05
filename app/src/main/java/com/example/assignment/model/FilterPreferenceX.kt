package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class FilterPreferenceX(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("value")
    val value: Int
)