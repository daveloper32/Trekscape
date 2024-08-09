package com.spherixlabs.trekscape.configure_key.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material.icons.rounded.NavigateBefore
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.configure_key.presentation.components.ItemToValidate
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
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.onAction(ConfigureKeyAction.OnScreenStarted)
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
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .safeDrawingPadding(),
    ) {
        if (state.isRequestingApiKey) {
            ZoomInOrOutAnimation(
                show = true,
            ) {
                RequestApiKey(state = state, onAction = onAction)
            }
        } else {
            ZoomInOrOutAnimation(
                show = true,
            ) {
                ValidateAndSaveApiKey(state = state, onAction = onAction)
            }
        }
    }
}

@Composable
private fun RequestApiKey(
    state    : ConfigureKeyState,
    onAction : (ConfigureKeyAction) -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text  = stringResource(R.string.lab_usage_limit),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text  = stringResource(R.string.lab_reached_limit_message),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Justify,
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
                enable   = state.enableValidate,
                onClick  = { onAction(ConfigureKeyAction.OnValidate) },
                content  = { Icon(Icons.AutoMirrored.Rounded.NavigateNext, contentDescription = "TrekScapeFloatingButton") },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun ValidateAndSaveApiKey(
    state    : ConfigureKeyState,
    onAction : (ConfigureKeyAction) -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (
                !state.isGeminiBeingValidated &&
                !state.isPlacesBeingValidated &&
                (!state.isGeminiValid ||
                !state.isPlacesValid)
            ) {
                ZoomInOrOutAnimation(
                    show = true,
                ) {
                    TrekScapeFloatingButton(
                        enable   = true,
                        onClick  = { onAction(ConfigureKeyAction.OnBack) },
                        content  = { Icon(Icons.Rounded.NavigateBefore, contentDescription = "TrekScapeFloatingButton") },
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(
                text  = stringResource(R.string.lab_validating_api_key),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ItemToValidate(
            text = stringResource(R.string.lab_gemini),
            isLoading = state.isGeminiBeingValidated,
            isValid = state.isGeminiValid,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        ItemToValidate(
            text = stringResource(R.string.lab_place),
            isLoading = state.isPlacesBeingValidated,
            isValid = state.isPlacesValid,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (
            !state.isGeminiBeingValidated &&
            !state.isPlacesBeingValidated &&
            (!state.isGeminiValid ||
            !state.isPlacesValid)
        ) {
            Text(
                text  = stringResource(R.string.lab_api_key_not_valid),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
        if (
            state.isGeminiValid &&
            state.isPlacesValid
        ) {
            Text(
                text  = stringResource(R.string.lab_api_key_valid),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ZoomInOrOutAnimation(
            show = state.enableSave,
            modifier = Modifier.align(Alignment.End)
        ) {
            TrekScapeFloatingButton(
                enable   = state.enableSave,
                onClick  = { onAction(ConfigureKeyAction.OnSave) },
                content  = { Icon(Icons.Rounded.Save, contentDescription = "TrekScapeFloatingButton") },
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