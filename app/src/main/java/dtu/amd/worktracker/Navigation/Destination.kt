package dtu.amd.worktracker.Navigation

// Based on lecture 5

sealed class Destination(val route: String) {

    object Home : Destination("home")

    object Add : Destination("add")

    object Edit : Destination("edit")

}