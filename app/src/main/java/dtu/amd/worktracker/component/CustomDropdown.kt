package dtu.amd.worktracker.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


// SOURCE: https://www.geeksforgeeks.org/drop-down-menu-in-android-using-jetpack-compose/
@Composable
fun CustomDropdown(label: String, selectedIndex: Int, options: List<String>, onChange: (String) -> Unit) {

    // Dropdown state
    var mExpanded by remember { mutableStateOf(false) }
    // Size of the textfield
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Selected option
    var selected by remember {
        mutableStateOf(options[selectedIndex])
    }

    // Icon based on dropdown state
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column() {

        OutlinedTextField(
            value = selected,
            onValueChange = { println("VALUE: " + it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                }
                .testTag("TextField"),
            label = {Text(label)},
            trailingIcon = {
                Icon(icon,"Expand",
                    // Change state on click
                    Modifier.clickable { mExpanded = !mExpanded }.testTag("Expand"))
            },
            readOnly = true,
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            options.forEach { label ->
                DropdownMenuItem(onClick = {
                    selected = label
                    mExpanded = false
                    onChange(label)
                }) {
                    Text(text = label)
                }
            }
        }
    }
}