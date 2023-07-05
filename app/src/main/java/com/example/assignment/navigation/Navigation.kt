package com.example.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.Screen
import com.example.assignment.view.Events
import com.example.assignment.view.FilterEvents
import com.example.assignment.viewmodel.EventViewModel

@Composable
fun Navigation(vm: EventViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.EventScreen.route) {
        composable(route = "event_screen") {
            Events(navController = navController, vm)
        }
        composable(
            route = Screen.FilterScreen.route,
        ) {
            FilterEvents(navController = navController,vm)
        }
    }
}