package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.welcome.presentation.screens.name_request.components.BackgroundNameRequestScreen
import com.spherixlabs.trekscape.welcome.presentation.screens.name_request.components.BodyNameRequestScreen
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
    val scrollState    = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }

    Box(Modifier.verticalScroll(scrollState)) {
        BackgroundNameRequestScreen(Modifier.fillMaxSize())
        BodyNameRequestScreen(
            state    = state,
            onAction = onAction,
            modifier = Modifier.align(Alignment.BottomCenter)
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