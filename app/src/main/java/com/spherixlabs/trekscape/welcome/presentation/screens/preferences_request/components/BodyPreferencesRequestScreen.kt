package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.welcome.presentation.domain.models.CategoryPreferenceModel
import com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.PreferencesRequestAction
import com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.PreferencesRequestState

@Composable
fun BodyPreferencesRequestScreen (
    list    : List<CategoryPreferenceModel>,
    state   : PreferencesRequestState,
    onAction: (PreferencesRequestAction) -> Unit
){
    val data = list[state.currentCategory]
    Column(
        horizontalAlignment = Alignment.Start,
        modifier            = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        IconButton(
            modifier = Modifier.alpha(if(state.showButtonBack) 1f else 0f),
            onClick  = { onAction.invoke(PreferencesRequestAction.OnPreviousCategoryPreference) },
            content  = {Icon( Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "ArrowBack")})
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text  = data.title.asString(),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text  = data.description.asString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(20.dp))
        PreferencesGridView(preferences = data.listPreferences, selections = state.preferencesSelected){
            onAction.invoke(PreferencesRequestAction.OnSelectOrDeselectPreference(it.id))
        }
    }
}