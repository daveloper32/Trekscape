package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeActionButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeTextField
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import kotlinx.coroutines.launch

@Composable
fun NameRequestScreenRoot(
    onGoToSetupPreferencesClick : () -> Unit,
    viewModel                   : NameRequestViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = context as Activity
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is NameRequestEvent.NavigateToSetupPreferences -> {
                keyboardController?.hide()
                onGoToSetupPreferencesClick()
            }
            is NameRequestEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    NameRequestScreen(
        state    = viewModel.state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun NameRequestScreen(
    state    : NameRequestState,
    onAction : (NameRequestAction) -> Unit,
) {
    AutoFinishBackPressHandler()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        TrekScapeTextField(
            text        = state.name,
            onValueChange = { value ->
                onAction(NameRequestAction.OnNameChanged(value))
            },
            keyboardType = KeyboardType.Text,
        )
        Spacer(modifier = Modifier.height(24.dp))
        TrekScapeActionButton(
            text = stringResource(id = R.string.lab_next),
            isLoading = state.isGoingNext,
            enabled = state.canGoNext && !state.isGoingNext,
            onClick = { onAction(NameRequestAction.OnNextClicked) }
        )
    }
}

@Preview
@Composable
private fun NameRequestScreenPreview() {
    TrekScapeTheme {
        NameRequestScreen(
            state = NameRequestState(),
            onAction = {},
        )
    }
}