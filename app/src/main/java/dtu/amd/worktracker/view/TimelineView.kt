package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import dtu.amd.worktracker.component.TimelineItem
import dtu.amd.worktracker.navigation.Destination
import androidx.compose.foundation.lazy.items
import dtu.amd.worktracker.viewmodel.TimelineViewModel

@Composable
fun TimelineView(
    navController: NavHostController,
    vm: TimelineViewModel = hiltViewModel()
) {
    // get list of work from viewmodel
    val workList = vm.getAllWork().collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(workList.value) { work ->
            TimelineItem(work, onClick = {
                navController.navigate(
                    Destination.Edit.routeWithId(work.id),
                    NavOptions.Builder()
                        .setPopUpTo("home", inclusive = false)
                        .build()
                )
            })
        }
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}
