package dtu.amd.worktracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dtu.amd.worktracker.view.AddView
import dtu.amd.worktracker.view.EditWorkView

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

        composable(
            Destination.Edit.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    this.nullable = false
                }
            )
        ) {
            EditWorkView(
                navController,
                it.arguments?.getInt("id") ?: 0
            )
        }

    }
}