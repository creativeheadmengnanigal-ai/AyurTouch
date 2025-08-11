package org.ayurtouch.project.doctor.screens.appointmentScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.add_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.doctor.utils.CustomButtonIconAndText
import org.ayurtouch.project.doctor.utils.CustomHeightShadowBox
import org.ayurtouch.project.doctor.utils.CustomTittle


@Composable
fun SymptomsForm() {
    var symptomEntries by remember {
        mutableStateOf(
            mutableListOf(
                SymptomEntry()
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        CustomTittle("Symptoms")

        symptomEntries.forEachIndexed { index, symptom ->

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedBoxField(
                label = "Nature",
                value = symptom.nature,
                onValueChange = { symptomEntries[index] = symptom.copy(nature = it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedBoxField(
                label = "Aggravating Factor",
                value = symptom.aggravatingFactor,
                onValueChange = { symptomEntries[index] = symptom.copy(aggravatingFactor = it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedBoxField(
                label = "Reducing Factor",
                value = symptom.reducingFactor,
                onValueChange = { symptomEntries[index] = symptom.copy(reducingFactor = it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedBoxField(
                label = "Onset",
                value = symptom.onset,
                onValueChange = { symptomEntries[index] = symptom.copy(onset = it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomDropdownField(
                label = "Symptom Status",
                selectedValue = symptom.symptomStatus,
                items = listOf("Present", "Absent", "Unknown"),
                onSelectedChange = { newValue ->
                    symptomEntries = symptomEntries.toMutableList().also { list ->
                        list[index] = symptom.copy(symptomStatus = newValue)
                    }
                }
            )


            Spacer(modifier = Modifier.height(10.dp))


            CustomDropdownField(
                label = "Intensity/Assessment Scale",
                selectedValue = symptom.intensityScale,
                items = listOf("Mild", "Moderate", "Severe"),
                onSelectedChange = { newValue ->
                    symptomEntries = symptomEntries.toMutableList().also { list ->
                        list[index] = symptom.copy(intensityScale = newValue)
                    }
                }
            )


            Spacer(modifier = Modifier.height(30.dp))
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Row {
                CustomButtonIconAndText(
                    buttonHeight = 30,
                    buttonWidth = 100,
                    buttonText = "ADD",
                    buttonTextColor = AppColors.MediumGray,
                    buttonTextSize = 14,
                    buttonRadius = 20,
                    buttonColor = AppColors.White,
                    buttonBorderColor = AppColors.OrangePrimary,
                    buttonIcon = Res.drawable.add_ic,
                    buttonIconSize = 20,
                    buttonIconColor = AppColors.OrangePrimary,
                    onClick = {
                        symptomEntries = (symptomEntries + SymptomEntry()) as MutableList<SymptomEntry>
                    }
                )
                Spacer(modifier = Modifier.width(20.dp))

                CustomButtonIconAndText(
                    buttonHeight = 30,
                    buttonWidth = 100,
                    buttonText = "Next",
                    buttonTextColor = AppColors.MediumGray,
                    buttonTextSize = 14,
                    buttonRadius = 20,
                    buttonColor = AppColors.SoftOrange,
                    buttonBorderColor = AppColors.SoftOrange,
                    buttonIcon = Res.drawable.add_ic,
                    buttonIconSize = 20,
                    buttonIconColor = AppColors.VibrantOrange,
                    onClick = {
                        // Use or save the data
                        println(symptomEntries)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}


@Composable
fun OutlinedBoxField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomHeightShadowBox(
        boxHeight = 60,
        horizontal = 0,
        vertical = 0,
        backgroundColor = AppColors.CreamyPeach,
        blurRadius = 20,
        cornerRadius = 60
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = label,
                    fontFamily = Monsetserrat500(),
                    fontSize = 14.sp,
                    color = AppColors.MediumGray
                )
            },
            modifier = Modifier
                .fillMaxSize(),
            textStyle = TextStyle(
                fontFamily = Monsetserrat500(),
                fontSize = 14.sp,
                color = AppColors.DarkGray
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.White,
                unfocusedBorderColor = AppColors.White
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownField(
    label: String,
    selectedValue: String,
    items: List<String>,
    onSelectedChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(
                    text = label,
                    fontFamily = Monsetserrat500(),
                    fontSize = 14.sp,
                    color = AppColors.MediumGray
                )
            },
            label = {
                Text(
                    text = label,
                    fontFamily = Monsetserrat500(),
                    fontSize = 14.sp,
                    color = AppColors.VibrantOrange
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            shape = RoundedCornerShape(30.dp),
            textStyle = TextStyle(
                fontFamily = Monsetserrat500(),
                fontSize = 14.sp,
                color = AppColors.DarkGray
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.CreamyPeach,
                unfocusedBorderColor = AppColors.CreamyPeach,
                focusedLabelColor = AppColors.VibrantOrange,
                unfocusedLabelColor = AppColors.MediumGray
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption,
                            fontFamily = Monsetserrat500(),
                            fontSize = 14.sp
                        )
                    },
                    onClick = {
                        onSelectedChange(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}
