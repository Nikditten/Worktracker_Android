package dtu.amd.worktracker.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dtu.amd.worktracker.R
import dtu.amd.worktracker.component.CustomTextField
import dtu.amd.worktracker.component.InputSection
import dtu.amd.worktracker.util.getMonthName
import dtu.amd.worktracker.viewmodel.SettingsViewModel

@Composable
fun SettingsView(
    vm: SettingsViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        InputSection(title = stringResource(R.string.salary)) {
            CustomTextField(
                text = vm.salary.toString(),
                label = stringResource(R.string.hourly_rate),
                onChange = { vm.salary = it.toDouble() })
        }

        InputSection(title = stringResource(R.string.taxes)) {
            CustomTextField(
                text = vm.tax_percentage.toString(),
                label = stringResource(R.string.tax_percentage),
                onChange = { vm.tax_percentage = it.toDouble() })

            CustomTextField(
                text = vm.tax_additional.toString(),
                label = stringResource(R.string.additional_taxes),
                onChange = { vm.tax_additional = it.toDouble() })

            CustomTextField(
                text = vm.tax_deduction.toString(),
                label = stringResource(R.string.tax_deduction),
                onChange = { vm.tax_deduction = it.toDouble() })
        }

        InputSection(title = stringResource(R.string.salary_period_ending)) {
            CustomTextField(
                text = vm.january.toString(),
                label = stringResource(R.string.last_day_in) + " " + (0).getMonthName(),
                onChange = { vm.january = it.toInt() })

            CustomTextField(
                text = vm.february.toString(),
                label = stringResource(R.string.last_day_in) + " " + (1).getMonthName(),
                onChange = { vm.february = it.toInt() })

            CustomTextField(
                text = vm.marts.toString(),
                label = stringResource(R.string.last_day_in) + " " + (2).getMonthName(),
                onChange = { vm.marts = it.toInt() })

            CustomTextField(
                text = vm.april.toString(),
                label = stringResource(R.string.last_day_in) + " " + (3).getMonthName(),
                onChange = { vm.april = it.toInt() })

            CustomTextField(
                text = vm.may.toString(),
                label = stringResource(R.string.last_day_in) + " " + (4).getMonthName(),
                onChange = { vm.may = it.toInt() })

            CustomTextField(
                text = vm.june.toString(),
                label = stringResource(R.string.last_day_in) + " " + (5).getMonthName(),
                onChange = { vm.june = it.toInt() })

            CustomTextField(
                text = vm.july.toString(),
                label = stringResource(R.string.last_day_in) + " " + (6).getMonthName(),
                onChange = { vm.july = it.toInt() })

            CustomTextField(
                text = vm.august.toString(),
                label = stringResource(R.string.last_day_in) + " " + (7).getMonthName(),
                onChange = { vm.august = it.toInt() })

            CustomTextField(
                text = vm.september.toString(),
                label = stringResource(R.string.last_day_in) + " " + (8).getMonthName(),
                onChange = { vm.september = it.toInt() })

            CustomTextField(
                text = vm.october.toString(),
                label = stringResource(R.string.last_day_in) + " " + (9).getMonthName(),
                onChange = { vm.october = it.toInt() })

            CustomTextField(
                text = vm.november.toString(),
                label = stringResource(R.string.last_day_in) + " " + (10).getMonthName(),
                onChange = { vm.november = it.toInt() })

            CustomTextField(
                text = vm.december.toString(),
                label = stringResource(R.string.last_day_in) + " " + (11).getMonthName(),
                onChange = { vm.december = it.toInt() })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(MaterialTheme.shapes.large),
            onClick = {

            }) {
            Text(stringResource(R.string.save))
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

}