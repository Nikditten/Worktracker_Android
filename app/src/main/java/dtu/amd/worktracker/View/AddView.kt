package dtu.amd.worktracker.View

import android.widget.ScrollView
import androidx.compose.foundation.background
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
import dtu.amd.worktracker.ui.theme.WorktrackerTheme
import java.util.*

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
            TextField(
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                label = {
                    Text("Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            TextField(
                value = company.value,
                onValueChange = {
                    company.value = it
                },
                label = {
                    Text("Company")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            TextField(
                value = paid.value,
                onValueChange = {
                    paid.value = it
                },
                label = {
                    Text("Paid")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
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