package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.animations.JumpingInfiniteAnimation

@Composable
fun TrekScapeSelectableText(
    text          : String,
    isSelected    : Boolean,
    selectedColor : Color = MaterialTheme.colorScheme.secondary,
    onClick       : () -> Unit,
    modifier      : Modifier = Modifier
) {
    if (isSelected) {
        JumpingInfiniteAnimation(
            targetValue = 18f,
        ) {
            TrekScapeSelectableTextInternalContent(
                text = text,
                isSelected = isSelected,
                selectedColor = selectedColor,
                onClick = onClick,
                modifier = modifier,
            )
        }
    } else {
        TrekScapeSelectableTextInternalContent(
            text = text,
            isSelected = isSelected,
            selectedColor = selectedColor,
            onClick = onClick,
            modifier = modifier,
        )
    }
}

@Composable
fun TrekScapeSelectableTextInternalContent(
    text          : String,
    isSelected    : Boolean,
    selectedColor : Color = MaterialTheme.colorScheme.secondary,
    onClick       : () -> Unit,
    modifier      : Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isSelected) selectedColor else Color.Transparent,
            )
            .border(
                width = 3.dp,
                color = if (isSelected) selectedColor else Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                vertical = 8.dp
            )
            .padding(
                horizontal = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSelected) {
            Image(
                imageVector = Icons.Filled.CheckCircleOutline,
                contentDescription = stringResource(id = R.string.lab_checked),
                colorFilter = ColorFilter.tint(
                    color = if (isSelected) {
                        Color.White
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text  = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = if (isSelected) { FontWeight.SemiBold } else { FontWeight.Normal },
            color = if (isSelected) {
                Color.White
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            textAlign = TextAlign.Center,
        )
    }
}