package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TrekScapeCheckboxWithTextOnLeft(
    isChecked    : Boolean = false,
    text         : String,
    onChecked    : (Boolean) -> Unit,
    checkedColor : Color = MaterialTheme.colorScheme.tertiary,
    modifier     : Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .offset(x = ((12).dp))
                .clickable {
                    onChecked(!isChecked)
                },
            text  = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
        )
        Checkbox(
            modifier = Modifier
                .offset(x = ((12).dp))
                .padding(end = 0.dp),
            checked         = isChecked,
            onCheckedChange = { value -> onChecked(value) },
            colors          = CheckboxDefaults.colors(
                checkedColor = checkedColor,
            ),
        )
    }
}