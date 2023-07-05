package com.example.assignment

sealed class Screen(val route:String) {
    object EventScreen :Screen("event_screen")
    object FilterScreen :Screen("filter_screen")

}