package com.example.assignment.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.model.*

import com.example.assignment.network.APIService
import com.example.assignment.network.token

import kotlinx.coroutines.launch


class EventViewModel : ViewModel() {
    private val _events = mutableStateListOf<EventsResponse>()
    private val _preferences = mutableStateListOf<EventsResponse>()
    private val _updateFilter = mutableStateListOf<EventsResponse>()
    val eventsData = mutableStateOf(EventsResponse())
    val preferenceData = mutableStateOf(EventsResponse())
    val updateFilterData = mutableStateOf(EventsResponse())
    var filteredPreferenceData = mutableMapOf<String, MutableList<FilterPreference>>()
    var filterPreferenceList = mutableListOf<FilterPreference?>()

    var checkedState by mutableStateOf(0)

    fun getEventsList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            var response = apiService.getEvents(
                token,
                100,
                "648ff9314fec14d2b272b74d",
                1,
                "opportunitiesToPlay",
                true,
                "available",
            )

            try {
                _events.clear()
                _events.addAll(
                    listOf(
                        apiService.getEvents(
                            token,
                            100,
                            "648ff9314fec14d2b272b74d",
                            1,
                            "opportunitiesToPlay",
                            true,
                            "available",
                        )
                    )
                )
                eventsData.value = eventsData.value.copy(response.data)

            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun getPreferenceList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()


            val response = apiService.getFilterPreferences(
                token,
                "opportunitiesToPlay",
                false
            )
            try {
                filterPreferenceList =
                    (response.data!!.filterPreferences as MutableList<FilterPreference>).toMutableList();
                println(response.data!!.filterPreferences)
                filterPreferenceKey(response.data!!.filterPreferences, filteredPreferenceData)

                println("Filtered Data:")
                filteredPreferenceData.forEach { (key, names) ->
                    println("Key: $key, Names: $names")
                }
                _preferences.clear()
                _preferences.addAll(
                    listOf(
                        apiService.getFilterPreferences(
                            token,
                            "opportunitiesToPlay",
                            false
                        )
                    )
                )
                preferenceData.value = preferenceData.value.copy(response.data)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun getUpdateFilterPreference() {
        val apiService = APIService.getInstance()
        val filterPreference = FilterPreferenceX("gender", "male", 1, 0)
        var filteredList = listOf(filterPreference)
        var body = FilteredList(filteredList)
        val pref = FilterPreferences(filterPreferenceList.toList())

        viewModelScope.launch {
            val response = apiService.updateFilterPreference(
                token,
                "opportunitiesToPlay",
                pref
            )
            try {
                _updateFilter.clear()
                _updateFilter.addAll(
                    listOf(
                        apiService.updateFilterPreference(
                            token,
                            "opportunitiesToPlay",
                            pref
                        )
                    )
                )
                updateFilterData.value = updateFilterData.value.copy(response.data)
                Log.d(
                    "harsh==",
                    "getUpdateFilterPreference: " + response.status + response.statusMessage
                )
            } catch (e: Exception) {
                throw e
            }


        }
    }

    private fun filterPreferenceKey(
        preferenceData: List<FilterPreference>,
        filteredPreferenceData: MutableMap<String, MutableList<FilterPreference>>
    ) {
        val listOfKeys = listOf("gender", "distance", "eventType", "format", "level")

        for (key in listOfKeys) {
            val values = preferenceData
                .filter { it.key == key }
                .map { it }

            if (values.isNotEmpty()) {
                filteredPreferenceData[key] = values.toMutableList()
            }
        }
    }

    /*dynamic list code */
    /*fun filterPreferenceKey(
        filteredData: List<FilterPreference>,
        filteredPreferenceData: MutableMap<String, MutableList<String>>
    ) {
        for (key in filteredData) {
            if (!filteredPreferenceData.containsKey(key.key)) {
                filteredPreferenceData[key.key] = mutableListOf()
            }
            filteredPreferenceData[key.key]?.add(key.name)
        }
    }*/
    fun convertIntToBoolean(value: Int): Boolean {
        return value != 0
    }

    fun findAndUpdateFilterList(key: String, name: String, status: Boolean) {

        var index = -1

        filterPreferenceList.forEachIndexed { ind, it ->

            if (it?.key.equals(key, ignoreCase = true) && it?.name.equals(name, ignoreCase = true)) {
                index = ind
            }

        }

        if (index != -1) {
            val updatedData = filterPreferenceList.toMutableList()
            updatedData[index] = updatedData[index]?.copy(status = if (status) 1 else 0)
            filterPreferenceList = updatedData
            Log.d("MyfilterPreferenceList", "findAndUpdateFilterList: $filterPreferenceList")
            getUpdateFilterPreference()
        }

    }
}
