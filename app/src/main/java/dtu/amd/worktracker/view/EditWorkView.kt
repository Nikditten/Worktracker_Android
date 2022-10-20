package dtu.amd.worktracker.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dtu.amd.worktracker.EditWorkViewModel
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection
import dtu.amd.worktracker.model.Work
import dtu.amd.worktracker.preview.data.Workitems
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EditWorkView(navController: NavHostController, id: Int) {

    val vm: EditWorkViewModel = EditWorkViewModel(id = id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add new work") },
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

                CustomTextField(
                    text = vm.date.toString(),
                    label = "Date",
                    enabled = false,
                    modifier = Modifier.clickable { vm.date = vm.showDatePickerDialog(context, vm.date) }
                )

                CustomTextField(text = vm.start.toString(), label = "Start", enabled = false, modifier = Modifier.clickable { vm.start = vm.showDatePickerDialog(context, vm.start) })

                CustomTextField(text = vm.end.toString(), label = "End", enabled = false, modifier = Modifier.clickable { vm.end = vm.showDatePickerDialog(context, vm.end) })

            }

            InputSection(title = "Lunch") {
                CustomTextField(text = vm.lunch_held.toString(), label = "Lunch held", onChange = { vm.lunch_held = it.toBoolean() })

                CustomTextField(text = vm.lunch_start.toString(), label = "Lunch start", enabled = false, modifier = Modifier.clickable { vm.lunch_start = vm.showDatePickerDialog(context, vm.lunch_start) })

                CustomTextField(text = vm.lunch_end.toString(), label = "Lunch end", enabled = false, modifier = Modifier.clickable { vm.lunch_end = vm.showDatePickerDialog(context, vm.lunch_end) })
            }

            InputSection(title = "Salary") {
                CustomTextField(text = vm.hourly_paid.toString(), label = "Hourly paid", onChange = { vm.hourly_paid = it.toBoolean() })

                CustomTextField(text = vm.paid.toString(), label = "Paid", onChange = { vm.paid = it.toDouble() })

                CustomTextField(text = vm.salary_period_month.toString(), label = "Salary period month", onChange = { vm.salary_period_month = it.toInt() })

                CustomTextField(text = vm.salary_period_year.toString(), label = "Salary period month", onChange = { vm.salary_period_year = it.toInt() })
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(MaterialTheme.shapes.large),
                onClick = {

                }) {
                Text("Save")
            }

        }
    }
}