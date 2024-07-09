package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.spherixlabs.trekscape.welcome.domain.model.PreferenceModel
import kotlinx.coroutines.delay

@Composable
fun PreferencesGridView(
    selections : List<PreferenceModel>,
    preferences: List<PreferenceModel>,
    modifier   : Modifier = Modifier,
    selectColor: Color = MaterialTheme.colorScheme.primary,
    onSelect   : (PreferenceModel) -> Unit,
){
    FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp, modifier = modifier) {
        preferences.forEach { preference ->
            PreferenceItem(
                preference  = preference,
                isSelect    = selections.find { it.id == preference.id } != null,
                selectColor = selectColor,
                onSelect    = { onSelect(preference) }
            )
        }
    }
}
@Composable
private fun PreferenceItem(
    preference : PreferenceModel,
    isSelect   : Boolean,
    selectColor: Color,
    onSelect   : () -> Unit
){
    var isJumping  by remember { mutableStateOf(false) }
    val jumpOffset by animateFloatAsState(
        targetValue   = if(isJumping) -10f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessLow
        ), label = ""
    )
    LaunchedEffect(isJumping) {
        if(isJumping){
            delay(200) //delay before returning to the initial position
            isJumping = false
        }
    }
    val backgroundColor = if (isSelect) selectColor else Color.LightGray
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier         = Modifier
            .offset(y = jumpOffset.dp)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable {
                isJumping = true
                onSelect()
            }
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                preference.icon,
                contentDescription = "preference-icon",
                modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text  = preference.title.asString(),
                style = MaterialTheme.typography.bodyMedium,
                color = if(isSelect) Color.White else MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}