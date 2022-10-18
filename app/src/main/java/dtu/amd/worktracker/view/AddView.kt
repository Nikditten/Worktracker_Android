package dtu.amd.worktracker.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection
import dtu.amd.worktracker.ui.theme.WorktrackerTheme

@Composable
fun AddView(navController: NavHostController) {

    val title = remember { mutableStateOf("") }
    val company = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val start = remember { mutableStateOf("") }
    val end = remember { mutableStateOf("") }
    val lunch_held = remember { mutableStateOf("") }
    val lunch_start = remember { mutableStateOf("") }
    val lunch_end = remember { mutableStateOf("") }
    val hourly_paid = remember { mutableStateOf("") }
    val paid = remember { mutableStateOf("") }
    val salary_period_month = remember { mutableStateOf("") }
    val salary_period_year = remember { mutableStateOf("") }

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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(10.dp)
        ) {
            InputSection(title = "General") {
                CustomTextField(text = title.value, label = "Title", onChange = { title.value = it })

                CustomTextField(text = company.value, label = "Company", onChange = { company.value = it })
            }

            InputSection(title = "Date") {

                CustomTextField(text = date.value, label = "Date", onChange = { date.value = it })

                CustomTextField(text = start.value, label = "Start", onChange = { start.value = it })

                CustomTextField(text = end.value, label = "End", onChange = { end.value = it })

            }

            InputSection(title = "Lunch") {
                CustomTextField(text = lunch_held.value, label = "Lunch held", onChange = { lunch_held.value = it })

                CustomTextField(text = lunch_start.value, label = "Lunch start", onChange = { lunch_start.value = it })

                CustomTextField(text = lunch_end.value, label = "Lunch end", onChange = { lunch_end.value = it })
            }

            InputSection(title = "Salary") {
                CustomTextField(text = hourly_paid.value, label = "Hourly paid", onChange = { hourly_paid.value = it })

                CustomTextField(text = paid.value, label = "Paid", onChange = { paid.value = it })

                CustomTextField(text = salary_period_month.value, label = "Salary period month", onChange = { salary_period_month.value = it })
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(MaterialTheme.shapes.medium),
                onClick = {

                }) {
                Text("Save")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPreview() {
    WorktrackerTheme {
        AddView(navController = rememberNavController())
    }
}