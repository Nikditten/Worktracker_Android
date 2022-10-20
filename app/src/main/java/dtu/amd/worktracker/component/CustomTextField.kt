package dtu.amd.worktracker.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// SOURCE: https://proandroiddev.com/the-big-form-with-jetpack-compose-7bec9cde157e

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    enabled: Boolean = true,
    onChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        enabled = enabled,
        value = text,
        onValueChange = {
            onChange(it)
        },
        label = {
            Text(label)
        },
    )
}