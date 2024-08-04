package com.spherixlabs.trekscape.configure_key.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.animations.ZoomInOrOutAnimation
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeFloatingButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeTextField

@Composable
fun ConfigureKeyScreenRoot(
    onDismiss  : ()-> Unit,
    viewModel  : ConfigureKeyViewModel = hiltViewModel(),
){
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            ConfigureEvent.GoBack -> onDismiss()
        }
    }
    ConfigureKeyScreen(
        state    = viewModel.state,
        onAction = viewModel::onAction
    )
}
@Composable
fun ConfigureKeyScreen(
    state    : ConfigureKeyState,
    onAction : (ConfigureKeyAction) -> Unit,
){
    Column(modifier = Modifier
        .padding(horizontal = 20.dp)
        .safeDrawingPadding()) {
        Text(
            text  = stringResource(R.string.lab_usage_limit),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text  = stringResource(R.string.lab_reached_limit_message),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        TrekScapeTextField(
            text          = state.apiKey,
            hint          = stringResource(R.string.lab_your_api_key),
            onValueChange = { value -> onAction(ConfigureKeyAction.OnApiKeyChanged(value))}
        )
        Spacer(modifier = Modifier.height(20.dp))
        ZoomInOrOutAnimation(show = true,modifier = Modifier.align(Alignment.End)){
            TrekScapeFloatingButton(
                enable   = state.enableSave,
                onClick  = { onAction(ConfigureKeyAction.SaveApiKey) },
                content  = { Icon(Icons.AutoMirrored.Rounded.NavigateNext, contentDescription = "TrekScapeFloatingButton") },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
@Preview
@Composable
private fun ConfigureKeyScreenPreview(){
    ConfigureKeyScreen(
        state    = ConfigureKeyState(),
        onAction = {}
    )
}