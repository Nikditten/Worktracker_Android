package dtu.amd.worktracker.Navigation

// Based on lecture 5

sealed class Destination(val route: String) {

    object Edit : Destination("edit")

}