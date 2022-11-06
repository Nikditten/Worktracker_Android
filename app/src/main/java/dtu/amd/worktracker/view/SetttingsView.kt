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
import dtu.amd.worktracker.viewmodel.AddViewModel
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
                text = vm.salary,
                label = stringResource(R.string.hourly_rate),
                onChange = { vm.salary = it })
        }

        InputSection(title = stringResource(R.string.taxes)) {
            CustomTextField(
                text = vm.tax_percentage,
                label = stringResource(R.string.tax_percentage),
                onChange = { vm.tax_percentage = it })

            CustomTextField(
                text = vm.tax_additional,
                label = stringResource(R.string.additional_taxes),
                onChange = { vm.tax_additional = it })

            CustomTextField(
                text = vm.tax_deduction,
                label = stringResource(R.string.tax_deduction),
                onChange = { vm.tax_deduction = it })
        }

        InputSection(title = stringResource(R.string.salary_period_ending)) {
            CustomTextField(
                text = vm.january,
                label = stringResource(R.string.last_day_in) + " " + (1).getMonthName(),
                onChange = { vm.january = it })

            CustomTextField(
                text = vm.february,
                label = stringResource(R.string.last_day_in) + " " + (2).getMonthName(),
                onChange = { vm.february = it })

            CustomTextField(
                text = vm.marts,
                label = stringResource(R.string.last_day_in) + " " + (3).getMonthName(),
                onChange = { vm.marts = it })

            CustomTextField(
                text = vm.april,
                label = stringResource(R.string.last_day_in) + " " + (4).getMonthName(),
                onChange = { vm.april = it })

            CustomTextField(
                text = vm.may,
                label = stringResource(R.string.last_day_in) + " " + (5).getMonthName(),
                onChange = { vm.may = it })

            CustomTextField(
                text = vm.june,
                label = stringResource(R.string.last_day_in) + " " + (6).getMonthName(),
                onChange = { vm.june = it })

            CustomTextField(
                text = vm.july,
                label = stringResource(R.string.last_day_in) + " " + (7).getMonthName(),
                onChange = { vm.july = it })

            CustomTextField(
                text = vm.august,
                label = stringResource(R.string.last_day_in) + " " + (8).getMonthName(),
                onChange = { vm.august = it })

            CustomTextField(
                text = vm.september,
                label = stringResource(R.string.last_day_in) + " " + (9).getMonthName(),
                onChange = { vm.september = it })

            CustomTextField(
                text = vm.october,
                label = stringResource(R.string.last_day_in) + " " + (10).getMonthName(),
                onChange = { vm.october = it })

            CustomTextField(
                text = vm.november,
                label = stringResource(R.string.last_day_in) + " " + (11).getMonthName(),
                onChange = { vm.november = it })

            CustomTextField(
                text = vm.december,
                label = stringResource(R.string.last_day_in) + " " + (12).getMonthName(),
                onChange = { vm.december = it })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(MaterialTheme.shapes.large),
            onClick = {
                vm.save()
            }) {
            Text(stringResource(R.string.save))
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

}