package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class FilteredList(
    @SerializedName("filterPreferences")
    val filterPreferences: List<FilterPreferenceX>
)