package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dtu.amd.worktracker.component.TimelineItem
import dtu.amd.worktracker.preview.data.Workitems

@Composable
fun TimelineView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        for (work in Workitems().getWork(true)) {
            TimelineItem(work)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}