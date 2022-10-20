package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection

@Composable
fun SettingsView() {
    val salary = remember { mutableStateOf(0) }

    val tax_deduction = remember { mutableStateOf(0.0) }
    val tax_percentage = remember { mutableStateOf(0.0) }
    val tax_additional = remember { mutableStateOf(0.0) }

    val january = remember { mutableStateOf(0) }
    val february = remember { mutableStateOf(0) }
    val marts = remember { mutableStateOf(0) }
    val april = remember { mutableStateOf(0) }
    val may = remember { mutableStateOf(0) }
    val june = remember { mutableStateOf(0) }
    val july = remember { mutableStateOf(0) }
    val august = remember { mutableStateOf(0) }
    val september = remember { mutableStateOf(0) }
    val october = remember { mutableStateOf(0) }
    val november = remember { mutableStateOf(0) }
    val december = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        InputSection(title = "Salary") {
            CustomTextField(text = salary.value.toString(), label = "Hourly rate", onChange = { salary.value = it.toInt() })
        }

        InputSection(title = "Taxes") {
            CustomTextField(text = tax_percentage.value.toString(), label = "Tax percentage (%)", onChange = { tax_percentage.value = it.toDouble() })

            CustomTextField(text = tax_additional.value.toString(), label = "Additional taxes (%)", onChange = { tax_additional.value = it.toDouble() })

            CustomTextField(text = tax_deduction.value.toString(), label = "Tax deduction (%)", onChange = { tax_deduction.value = it.toDouble() })
        }

        InputSection(title = "Salary period endings") {
            CustomTextField(text = january.value.toString(), label = "Last day in January", onChange = { january.value = it.toInt() })

            CustomTextField(text = february.value.toString(), label = "Last day in February", onChange = { february.value = it.toInt() })

            CustomTextField(text = marts.value.toString(), label = "Last day in Marts", onChange = { marts.value = it.toInt() })

            CustomTextField(text = april.value.toString(), label = "Last day in April", onChange = { april.value = it.toInt() })

            CustomTextField(text = may.value.toString(), label = "Last day in May", onChange = { may.value = it.toInt() })

            CustomTextField(text = june.value.toString(), label = "Last day in June", onChange = { june.value = it.toInt() })

            CustomTextField(text = july.value.toString(), label = "Last day in July", onChange = { july.value = it.toInt() })

            CustomTextField(text = august.value.toString(), label = "Last day in August", onChange = { august.value = it.toInt() })

            CustomTextField(text = september.value.toString(), label = "Last day in September", onChange = { september.value = it.toInt() })

            CustomTextField(text = october.value.toString(), label = "Last day in October", onChange = { october.value = it.toInt() })

            CustomTextField(text = november.value.toString(), label = "Last day in November", onChange = { november.value = it.toInt() })

            CustomTextField(text = december.value.toString(), label = "Last day in December", onChange = { december.value = it.toInt() })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(MaterialTheme.shapes.large),
            onClick = {

            }) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}