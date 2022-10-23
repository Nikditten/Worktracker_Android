package dtu.amd.worktracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dtu.amd.worktracker.model.Work
import dtu.amd.worktracker.preview.data.Workitems
import dtu.amd.worktracker.ui.theme.WorktrackerTheme
import dtu.amd.worktracker.util.AsDate
import java.text.SimpleDateFormat

@Composable
fun TimelineItem(workItem: Work, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() },
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.onSecondary)
                    .padding(20.dp)
            ) {
                Icon(
                    Icons.Filled.Work,
                    contentDescription = "Work",
                    tint = MaterialTheme.colors.secondary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(

                ) {
                    Text(
                        text = workItem.title,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = workItem.company,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        // SOURCE: https://stackoverflow.com/questions/57402045/how-to-format-in-kotlin-date-in-string-or-timestamp-to-my-preferred-format
                        text = workItem.date.AsDate(),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = "+" + workItem.paid.toString(),
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TimelineItemPreview() {
    WorktrackerTheme {
        TimelineItem(Workitems().getWork(true).first())
    }
}