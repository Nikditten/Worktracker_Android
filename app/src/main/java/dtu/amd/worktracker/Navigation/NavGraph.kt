package dtu.amd.worktracker.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dtu.amd.worktracker.View.AddView
import dtu.amd.worktracker.View.EditWorkView
import dtu.amd.worktracker.View.HomeView


// Based on Lecture 5

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            NavigationBar(navController = navController)
        }

        composable(Destination.Add.route) {
            AddView(navController = navController)
        }

        composable(Destination.Edit.route) {
            EditWorkView(navController = navController)
        }

    }
}