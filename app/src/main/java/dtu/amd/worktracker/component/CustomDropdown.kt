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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


// SOURCE: https://www.geeksforgeeks.org/drop-down-menu-in-android-using-jetpack-compose/
@Composable
fun CustomDropdown(label: String, selectedIndex: Int, options: List<String>, onChange: (String) -> Unit) {

    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    var selected by remember {
        mutableStateOf(options[selectedIndex])
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column() {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = selected,
            onValueChange = { onChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text(label)},
            trailingIcon = {
                Icon(icon,"Expand",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            readOnly = true,
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
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
                }) {
                    Text(text = label)
                }
            }
        }
    }
}