package dtu.amd.worktracker.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import dtu.amd.worktracker.view.HomeView
import dtu.amd.worktracker.view.SettingsView
import dtu.amd.worktracker.view.TimelineView
import dtu.amd.worktracker.viewmodel.MainViewModel

@Composable
fun NavigationBar(navController: NavHostController) {
    // SOURCE: https://developer.android.com/jetpack/compose/layouts/material

    val showFilter = remember { mutableStateOf(true) }

    val selectedIndex = remember { mutableStateOf(0) }
    val selectedView = remember {
        mutableStateOf(
            "Home"
        )
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(
                    Destination.Add.route,
                    NavOptions.Builder()
                        .setPopUpTo(Destination.Home.route, inclusive = false)
                        .build()
                )
            }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        },
        isFloatingActionButtonDocked = true,
        topBar = {
            TopAppBar(
                title = { Text(selectedView.value) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                actions = {
                    if (selectedIndex.value == 0) {
                        IconButton(onClick = { !showFilter.value }) {
                            Icon(
                                Icons.Filled.FilterAlt,
                                "Filter",
                                tint = showFilter.value.let {
                                    if (it) Color.White else Color.Gray
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {

            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                // SOURCE: https://www.jetpackcompose.net/scaffold
                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Home, "Home")
                },
                    label = { Text(text = "Home") },
                    selected = (selectedIndex.value == 0),
                    onClick = {
                        selectedIndex.value = 0
                        selectedView.value = "Home"
                    })

                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Timeline, "Timeline")
                },
                    label = { Text(text = "Timeline") },
                    selected = (selectedIndex.value == 1),
                    onClick = {
                        selectedIndex.value = 1
                        selectedView.value = "Timeline"
                    })

                BottomNavigationItem(icon = {
                    Icon(imageVector = Icons.Filled.Settings, "Settings")
                },
                    label = { Text(text = "Settings") },
                    selected = (selectedIndex.value == 2),
                    onClick = {
                        selectedIndex.value = 2
                        selectedView.value = "Settings"
                    })

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(10.dp)) {
            when (selectedIndex.value) {
                0 -> HomeView(showFilter.value)
                1 -> TimelineView(navController = navController)
                2 -> SettingsView()
                else -> HomeView(showFilter.value)
            }
        }
    }
}