package dtu.amd.worktracker.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.viewmodel.EditViewModel
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection
import dtu.amd.worktracker.util.AsDate
import dtu.amd.worktracker.util.AsTime

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditWorkView(
    navController: NavHostController,
    vm: EditViewModel = hiltViewModel()) {

    var hourly_paid by remember { mutableStateOf(vm.paid > 0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit shift") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
            )
        },
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(10.dp)
        ) {
            InputSection(title = "General") {
                CustomTextField(text = vm.title, label = "Title", onChange = { vm.title = it })

                CustomTextField(text = vm.company, label = "Company", onChange = { vm.company = it })
            }

            InputSection(title = "Date") {

                CustomTextField(text = vm.date.AsDate(), label = "Date", enabled = false, modifier = Modifier.clickable { vm.showDatePickerDialog(context) })

                CustomTextField(text = vm.start.AsTime(), label = "Start", enabled = false, modifier = Modifier.clickable { vm.showTimePickerDialog(context, "start") })

                CustomTextField(text = vm.end.AsTime(), label = "End", enabled = false, modifier = Modifier.clickable { vm.showTimePickerDialog(context, "end") })

            }

            InputSection(title = "Lunch") {
                CustomDropdown(
                    label = "Lunch",
                    options = listOf("Held", "Not held"),
                    selectedIndex = if (vm.lunch_held) 0 else 1,
                    onChange = {
                        if (it == "Held") {
                            vm.lunch_held = true
                        } else {
                            vm.lunch_held = false
                        }
                    }
                )

                if (vm.lunch_held) {
                    CustomTextField(text = vm.lunch_start.AsTime(), label = "Lunch start", enabled = false, modifier = Modifier.clickable { vm.showTimePickerDialog(context, "lunch_start") })

                    CustomTextField(text = vm.lunch_end.AsTime(), label = "Lunch end", enabled = false, modifier = Modifier.clickable { vm.showTimePickerDialog(context, "lunch_end") })
                }
            }

            InputSection(title = "Salary") {
                CustomDropdown(
                    label = "Payment type",
                    options = listOf("Paid by hour", "One time fee"),
                    selectedIndex = if (hourly_paid) 0 else 1,
                    onChange = {
                        if (it == "Paid by hour") {
                            hourly_paid = true
                        } else {
                            hourly_paid = false
                        }
                    }
                )

                if (hourly_paid) {
                    CustomTextField(text = vm.paid.toString(), label = "Paid", onChange = { vm.paid = it.toDouble() })
                } else {
                    CustomTextField(text = vm.one_time_fee.toString(), label = "One time fee", onChange = { vm.one_time_fee = it.toDouble() })
                }

                CustomDropdown(
                    label = "Salary period month",
                    options = vm.months,
                    selectedIndex = vm.salary_period_month - 1,
                    onChange = {
                        vm.salary_period_month = vm.months.indexOf(it) + 1
                    }
                )

                CustomDropdown(
                    label = "Salary period year",
                    options = vm.years,
                    selectedIndex = vm.years.indexOf(vm.salary_period_year.toString()),
                    onChange = {
                        vm.salary_period_year = it.toInt()
                    }
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(MaterialTheme.shapes.large),
                onClick = {
                    vm.save()
                    navController.popBackStack()
                }) {
                Text("Save")
            }

        }
    }
}