package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dtu.amd.worktracker.StatView


@Composable
fun HomeView() {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StatView("Earnings", "434300 DKK")

        StatView("Expected payout", "340000 DKK")

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatView("Hours", "37", half = true)

            StatView("Shifts", "12", half = true, right = true)
        }
    }

}