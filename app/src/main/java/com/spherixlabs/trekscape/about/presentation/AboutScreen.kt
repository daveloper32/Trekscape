package com.spherixlabs.trekscape.about.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.about.presentation.components.ItemDeveloperProfile
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.intent.IntentUtils

@Composable
fun AboutScreenRoot(
    viewModel: AboutViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is AboutEvent.GoToSomeUrl -> {
                IntentUtils.goToWebPage(
                    context = context,
                    url     = event.url,
                )
            }
            is AboutEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    AboutScreen(
        state    = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AboutScreen(
    state    : AboutState,
    onAction : (AboutAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
            ).padding(
                bottom = 32.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Image(
                painter            = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier           = Modifier
                    .padding(end = 10.dp)
                    .size(70.dp)
                    .clip(CircleShape))
            Spacer(modifier = Modifier.size(8.dp))
        }
        item {
            Text(
                text = stringResource(
                    id = R.string.lab_about,
                ) + " " + stringResource(
                    id = R.string.app_name
                ),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            Text(
                text  = stringResource(id = R.string.lab_about_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Justify,
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.isLoadingDeveloperProfiles) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text(
                    text = stringResource(
                        id = R.string.lab_development_team,
                    ) ,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.size(8.dp))
        }
        items(
            count = state.developerProfiles.size,
            key = { index -> state.developerProfiles[index].userName },
        ) {
            ItemDeveloperProfile(
                developerProfile = state.developerProfiles[it],
                onClick = { onAction(AboutAction.OnSomeDeveloperProfileClicked(state.developerProfiles[it])) }
            )
        }
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    TrekScapeTheme {
        AboutScreen(
            state = AboutState(),
            onAction = {},
        )
    }
}