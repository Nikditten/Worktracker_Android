package dtu.amd.worktracker.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dtu.amd.worktracker.R
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection
import dtu.amd.worktracker.ui.theme.WorktrackerTheme
import dtu.amd.worktracker.util.AsDate
import dtu.amd.worktracker.util.AsTime
import dtu.amd.worktracker.viewmodel.AddViewModel

@Composable
fun AddView(
    navController: NavHostController,
    vm: AddViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_new_work)) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    // Stack er defineret i NavigationBar.kt
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
                    }
                },
            )
        },

        ) { padding ->
        val context = LocalContext.current
        Column(
            modifier = Modifier
                    // Bruges til at scroll
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(10.dp)
        ) {
            InputSection(title = stringResource(R.string.generel)) {
                CustomTextField(
                    text = vm.title,
                    label = stringResource(R.string.title),
                    onChange = { vm.title = it })

                CustomTextField(
                    text = vm.company,
                    label = stringResource(R.string.company),
                    onChange = { vm.company = it })
            }

            InputSection(title = stringResource(R.string.date)) {

                CustomTextField(
                    text = vm.date.AsDate(),
                    label = stringResource(R.string.date),
                    enabled = false,
                    modifier = Modifier.clickable { vm.showDatePickerDialog(context) })

                CustomTextField(
                    text = vm.start.AsTime(),
                    label = stringResource(R.string.start),
                    enabled = false,
                    modifier = Modifier.clickable { vm.showTimePickerDialog(context, "start") })

                CustomTextField(
                    text = vm.end.AsTime(),
                    label = stringResource(R.string.end),
                    enabled = false,
                    modifier = Modifier.clickable { vm.showTimePickerDialog(context, "end") })

            }

            InputSection(title = stringResource(R.string.lunch)) {
                CustomDropdown(
                    label = "Lunch",
                    options = listOf(
                        stringResource(R.string.held),
                        stringResource(R.string.not_held)
                    ),
                    selectedIndex = if (vm.lunch_held) 0 else 1,
                    onChange = {
                        vm.lunch_held = it == "Held"
                    }
                )

                if (vm.lunch_held) {
                    CustomTextField(
                        text = vm.lunch_start.AsTime(),
                        label = stringResource(R.string.lunch_start),
                        enabled = false,
                        modifier = Modifier.clickable {
                            vm.showTimePickerDialog(
                                context,
                                "lunch_start"
                            )
                        })

                    CustomTextField(
                        text = vm.lunch_end.AsTime(),
                        label = stringResource(R.string.lunch_end),
                        enabled = false,
                        modifier = Modifier.clickable {
                            vm.showTimePickerDialog(
                                context,
                                "lunch_end"
                            )
                        })
                }
            }

            InputSection(title = stringResource(R.string.salary)) {

                CustomTextField(
                    text = vm.hourly_rate,
                    label = stringResource(R.string.paid),
                    onChange = { vm.hourly_rate = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                CustomDropdown(
                    label = stringResource(R.string.salary_period_month),
                    options = vm.months,
                    selectedIndex = vm.salary_period_month - 1,
                    onChange = {
                        vm.salary_period_month = vm.months.indexOf(it) + 1
                    }
                )

                CustomDropdown(
                    label = stringResource(R.string.salary_period_year),
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