package dtu.amd.worktracker.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dtu.amd.worktracker.R
import dtu.amd.worktracker.StatView
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.util.RoundTo2Decimals
import dtu.amd.worktracker.util.getMonthName
import dtu.amd.worktracker.viewmodel.MainViewModel


@Composable
fun HomeView(
    showFilter: Boolean,
    vm: MainViewModel = hiltViewModel()
) {

    // Collect flows from viewmodel
    val earnings = vm.getEarnings().collectAsState(initial = 0.0)
    val expectedEarnings = vm.getExptectedEarnings()
    val hours = vm.getHours().collectAsState(initial = 0.0)
    val shifts = vm.getShifts().collectAsState(initial = 0)

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (showFilter) {
            item {
                Row(
                    modifier = Modifier
                        .padding()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = {
                        vm.decrementPeriod()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowLeft,
                            contentDescription = "Previous month"
                        )
                    }

                    Text(
                        text = if (vm.monthlyPeriod) {
                            vm.selectedMonth.getMonthName() + " " + vm.selectedYear
                        } else {
                            vm.selectedYear.toString()
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                                // SOURCE: https://developer.android.com/jetpack/compose/gestures
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = { vm.resetPeriod() },
                                    onTap = { vm.changePeriod() }
                                )
                            }
                    )

                    IconButton(onClick = {
                        vm.incrementPeriod()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowRight,
                            contentDescription = "Next month"
                        )
                    }
                }
            }
        }

        item {StatView(title = stringResource(R.string.earnings), value = "${earnings.value ?: 0.0}")}

        item {StatView(title = stringResource(R.string.expected_payout), value = "${expectedEarnings.RoundTo2Decimals()}")}

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StatView(title = stringResource(R.string.hours), value = "${hours.value ?: 0.0}", half = true)

                StatView(title = stringResource(R.string.shifts), value = "${shifts.value ?: 0}", half = true, right = true)
            }
        }
    }

}