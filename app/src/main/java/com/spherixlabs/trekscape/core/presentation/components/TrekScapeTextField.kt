package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

@Composable
fun TrekScapeTextField(
    text           : String,
    onValueChange  : (String) -> Unit,
    startIcon      : ImageVector? = null,
    endIcon        : ImageVector? = null,
    hint           : String = EMPTY_STR,
    title          : String? = null,
    error          : String? = null,
    keyboardType   : KeyboardType = KeyboardType.Text,
    additionalInfo : String? = null,
    modifier       : Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            if (!error.isNullOrBlank()) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                )
            } else if (!additionalInfo.isNullOrBlank()) {
                Text(
                    text = additionalInfo,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onBackground,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) {
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.05f,
                        )
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isFocused) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.4f,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                        innerTextField()
                    }
                    if (endIcon != null) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun RuniqueTextFieldPreview() {
    TrekScapeTheme {
        TrekScapeTextField(
            text           = "",
            onValueChange  = {},
            startIcon = Icons.Rounded.Email,
            endIcon   = Icons.Rounded.Check,
            hint      = "example@test.com",
            title     = "Email",
            additionalInfo = "Must be a valid email",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}