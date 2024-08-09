package com.spherixlabs.trekscape.profile.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeSelectableText
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.home.domain.utils.toUiText
import com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.components.PreferencesGridView

@Composable
fun ProfileScreenRoot(
    onGoToGeneralPreferences  : () -> Unit,
    onGoToLocationPreferences : () -> Unit,
    onGoToEditApiKey          : () -> Unit,
    viewModel                 : ProfileViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.onAction(ProfileAction.OnScreenStarted)
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            ProfileEvent.GoToGeneralPreferences -> onGoToGeneralPreferences()
            ProfileEvent.GoToLocationPreferences -> onGoToLocationPreferences()
            is ProfileEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            ProfileEvent.GoToEditApiKey -> onGoToEditApiKey()
        }
    }
    ProfileScreen(
        state    = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ProfileScreen(
    state    : ProfileState,
    onAction : (ProfileAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 24.dp
            )
            .padding(
                bottom = 24.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        if (state.userName.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter            = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "",
                    modifier           = Modifier
                        .padding(end = 10.dp)
                        .size(70.dp)
                        .clip(CircleShape))
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(
                        id = R.string.lab_hello_user,
                        state.userName
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(1.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text  = stringResource(id = R.string.lab_selected_preferences),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Button(
                    onClick = { onAction(ProfileAction.OnEditGeneralPreferences) }
                ) {
                    Image(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(id = R.string.lab_edit)
                    )
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text  = stringResource(id = R.string.nature_and_adventure),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(8.dp))
            PreferencesGridView(
                preferences = state.natureAdventurePreferences,
                selections  = emptyList(),
            ) {}
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text  = stringResource(id = R.string.culture_and_history),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(8.dp))
            PreferencesGridView(
                preferences = state.cultureHistoryPreferences,
                selections  = emptyList(),
            ) {}
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text  = stringResource(id = R.string.relaxation_and_wellbeing),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(8.dp))
            PreferencesGridView(
                preferences = state.relaxationPreferences,
                selections  = emptyList(),
            ) {}
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text  = stringResource(id = R.string.lab_location_preferences),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Button(
                    onClick = { onAction(ProfileAction.OnEditLocationPreferences) }
                ) {
                    Image(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(id = R.string.lab_edit)
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            TrekScapeSelectableText(
                text = state.locationPreference.toUiText().asString(),
                isSelected = true,
                enableAnimation = false,
                onClick = {},
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text  = stringResource(R.string.lab_add_own_api_key),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Button(
                    onClick = {
                        if (state.apiKey.isEmpty()) {
                            onAction(ProfileAction.OnEditApiKey)
                        } else {
                            onAction(ProfileAction.OnRemoveApiKey)
                        }
                    }
                ) {
                    Image(
                        imageVector = if (state.apiKey.isEmpty()) Icons.Rounded.Edit else Icons.Rounded.DeleteForever,
                        contentDescription = stringResource(id = R.string.lab_edit)
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Icon(Icons.Rounded.Key, contentDescription = "icon-Key")
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = if (state.apiKey.isEmpty()) {
                        stringResource(R.string.lab_not_configured)
                    } else {
                        "*".repeat(state.apiKey.count())
                    }
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    TrekScapeTheme {
        ProfileScreen(
            state = ProfileState(),
            onAction = {},
        )
    }
}