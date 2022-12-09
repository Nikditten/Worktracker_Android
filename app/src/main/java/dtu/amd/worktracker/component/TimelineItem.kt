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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dtu.amd.worktracker.R
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.AsDate
import dtu.amd.worktracker.util.RoundTo2Decimals

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
                    modifier = Modifier
                        .padding(end = 15.dp)
                ) {
                    Text(
                        text = workItem.title,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = workItem.company,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        // SOURCE: https://stackoverflow.com/questions/57402045/how-to-format-in-kotlin-date-in-string-or-timestamp-to-my-preferred-format
                        text = workItem.date.AsDate(),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(end = 10.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "+" + workItem.paid.RoundTo2Decimals(),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = workItem.hours.toString() + " " + stringResource(R.string.hours),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
