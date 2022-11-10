package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dtu.amd.worktracker.R
import dtu.amd.worktracker.StatView
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.util.getMonthName
import dtu.amd.worktracker.viewmodel.MainViewModel


@Composable
fun HomeView(
    showFilter: Boolean,
    vm: MainViewModel = hiltViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (showFilter) {
            Row(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = {
                    vm.selectedMonth--
                    if (vm.selectedMonth < 1) {
                        vm.selectedMonth = 12
                        vm.selectedYear--
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowLeft,
                        contentDescription = "Previous month"
                    )
                }

                Text(
                    text = vm.selectedMonth.getMonthName() + " " + vm.selectedYear,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                )

                IconButton(onClick = {
                    vm.selectedMonth++
                    if (vm.selectedMonth > 12) {
                        vm.selectedMonth = 1
                        vm.selectedYear++
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowRight,
                        contentDescription = "Next month"
                    )
                }
            }
        }

        StatView(title = stringResource(R.string.earnings), value = "${vm.earnings}")

        StatView(title = stringResource(R.string.expected_payout), value = "${vm.expectedIncome}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatView(title = stringResource(R.string.hours), value = "${vm.hours}", half = true)

            StatView(title = stringResource(R.string.shifts), value = "${vm.shifts}", half = true, right = true)
        }
    }

}