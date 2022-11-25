package dtu.amd.worktracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dtu.amd.worktracker.ui.theme.WorktrackerTheme

@Composable
fun StatView(title: String, value: String, half: Boolean = false, right: Boolean = false) {
    // https://stackoverflow.com/questions/68919900/screen-width-and-height-in-jetpack-compose
    // Get screen size
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .padding(end = if (!right && half) 10.dp else 0.dp)
            .padding(start = if (right && half) 10.dp else 0.dp)
            .width(if (half) screenWidth / 2 else screenWidth)
            .height(screenHeight / 7)
            .background(MaterialTheme.colors.secondary),
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 25.sp,
                color = MaterialTheme.colors.onSecondary
            )

            Text(
                text = value,
                fontSize = 35.sp,
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatPreview() {
    WorktrackerTheme {
        StatView("Indtjening", "32000 DKK")
    }
}