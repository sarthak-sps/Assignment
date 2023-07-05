package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.assignment.navigation.Navigation
import com.example.assignment.ui.theme.AssignmentTheme
import com.example.assignment.viewmodel.EventViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                // A surface container using the 'background' color from the theme
                val vm : EventViewModel by viewModels()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                        FilterEvents()
                    Navigation(vm)

                }
            }
        }
    }
}


