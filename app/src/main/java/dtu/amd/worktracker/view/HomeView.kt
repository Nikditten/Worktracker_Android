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
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.viewmodel.MainViewModel


@Composable
fun HomeView(
    showFilter: Boolean = true,
    vm: MainViewModel = hiltViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (showFilter) {
            CustomDropdown(
                label = stringResource(R.string.period),
                options = listOf(stringResource(R.string.month), stringResource(R.string.year)),
                selectedIndex = 0,
                onChange = {
                    vm.monthlyPeriod = it == "Month"
                }
            )

            CustomDropdown(
                label = stringResource(R.string.salary_period_month),
                options = vm.months,
                selectedIndex = vm.selectedMonth - 1,
                onChange = {
                    vm.selectedMonth = vm.months.indexOf(it) + 1
                }
            )

            CustomDropdown(
                label = stringResource(R.string.salary_period_year),
                options = vm.years,
                selectedIndex = vm.years.indexOf(vm.selectedYear.toString()),
                onChange = {
                    vm.selectedYear = it.toInt()
                },
            )
        }

        StatView(title = stringResource(R.string.earnings), value = "${vm.getEarnings()} DKK")

        StatView(title = stringResource(R.string.expected_payout), value = "${vm.getExpectedPayout()} DKK")

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatView(title = stringResource(R.string.hours), value = "${vm.getHours()}", half = true)

            StatView(title = stringResource(R.string.shifts), value = "${vm.getShifts()}", half = true, right = true)
        }
    }

}