package dtu.amd.worktracker.navigation

// Based on lecture 5

// Enum af objekter/klasser
sealed class Destination(val route: String) {

    // Routing for the app

    object Home : Destination("home")

    object Add : Destination("add")

    object Edit : Destination("edit/{id}") {
        fun routeWithId(id: Int) = route.replace("{id}", id.toString())
    }

}