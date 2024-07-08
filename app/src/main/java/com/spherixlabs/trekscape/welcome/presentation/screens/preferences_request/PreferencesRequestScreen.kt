package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.core.presentation.Animations.ZoomInOrOutAnimation
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeFloatingButton
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.welcome.presentation.components.CircleView
import com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request.components.BodyPreferencesRequestScreen

@Composable
fun PreferencesRequestScreenRoot(
    viewModel : PreferencesRequestViewModel = hiltViewModel(),
) {
    PreferencesRequestScreen(
        state      = viewModel.state,
        onAction   = viewModel::onAction,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun PreferencesRequestScreen(
    state    : PreferencesRequestState,
    onAction : (PreferencesRequestAction) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            ZoomInOrOutAnimation(show = state.showNext){
                TrekScapeFloatingButton(
                    onClick  = { onAction.invoke(PreferencesRequestAction.OnNextCategoryPreference)},
                    content  = { Icon(Icons.AutoMirrored.Rounded.NavigateNext, contentDescription = "TrekScapeFloatingButton") },
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircleView(
                radius      = 200.dp,
                colorCircle = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                modifier    = Modifier.padding(50.dp)
            )
            BodyPreferencesRequestScreen(
                state    = state,
                onAction = onAction
            )
        }
    }
}

@Preview
@Composable
private fun PreferencesRequestScreenPreview() {
    TrekScapeTheme {
        PreferencesRequestScreen(
            state = PreferencesRequestState(),
            onAction = {  },
        )
    }
}