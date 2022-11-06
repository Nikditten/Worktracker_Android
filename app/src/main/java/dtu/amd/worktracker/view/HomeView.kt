package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dtu.amd.worktracker.R
import dtu.amd.worktracker.StatView
import dtu.amd.worktracker.viewmodel.MainViewModel


@Composable
fun HomeView(
    vm: MainViewModel = hiltViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StatView(title = stringResource(R.string.earnings), value = "${vm.getEarningsByMonth()} DKK")

        StatView(title = stringResource(R.string.expected_payout), value = "340000 DKK")

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatView(title = stringResource(R.string.hours), value = "37", half = true)

            StatView(title = stringResource(R.string.shifts), value = "12", half = true, right = true)
        }
    }

}