package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import dtu.amd.worktracker.component.TimelineItem
import dtu.amd.worktracker.navigation.Destination
import dtu.amd.worktracker.preview.data.Workitems
import dtu.amd.worktracker.viewmodel.TimelineViewModel

@Composable
fun TimelineView(navController: NavHostController) {

    val vm: TimelineViewModel = TimelineViewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        for (work in vm.workList) {
            TimelineItem(work, onClick = {
                navController.navigate(
                    Destination.Edit.routeWithId(work.id),
                    NavOptions.Builder()
                        .setPopUpTo("home", inclusive = false)
                        .build()
                )
            })
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}