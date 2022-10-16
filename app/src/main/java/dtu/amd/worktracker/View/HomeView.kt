package dtu.amd.worktracker.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import dtu.amd.worktracker.StatView


@Composable
fun HomeView() {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StatView("Indtjening", "434300 DKK")

        StatView("Forventet udbetaling", "340000 DKK")

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatView("Timer", "37", half = true)

            StatView("Vagter", "12", half = true)
        }
    }

}