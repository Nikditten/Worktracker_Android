package dtu.amd.worktracker.Navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dtu.amd.worktracker.View.HomeView
import dtu.amd.worktracker.View.SettingsView
import dtu.amd.worktracker.View.TimelineView

@Composable
fun NavigationBar() {
    // SOURCE: https://developer.android.com/jetpack/compose/layouts/material
    val selectedIndex = remember { mutableStateOf(0) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {

            BottomAppBar(
                // Defaults to null, that is, No cutout
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                // SOURCE: https://www.jetpackcompose.net/scaffold
                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Home,"Home")
                },
                    label = { Text(text = "Home") },
                    selected = (selectedIndex.value == 0),
                    onClick = {
                        selectedIndex.value = 0
                    })

                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Timeline,"Timeline")
                },
                    label = { Text(text = "Timeline") },
                    selected = (selectedIndex.value == 1),
                    onClick = {
                        selectedIndex.value = 1
                    })

                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Settings,"Settings")
                },
                    label = { Text(text = "Settings") },
                    selected = (selectedIndex.value == 2),
                    onClick = {
                        selectedIndex.value = 2
                    })

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) {
        // Content
        if (selectedIndex.value == 0) {
            HomeView()
        } else if (selectedIndex.value == 1) {
            TimelineView()
        } else if (selectedIndex.value == 2) {
            SettingsView()
        }
    }
}