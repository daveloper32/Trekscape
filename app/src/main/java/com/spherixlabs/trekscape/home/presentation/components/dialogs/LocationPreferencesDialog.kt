package com.spherixlabs.trekscape.home.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeActionButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeCheckboxWithTextOnLeft
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeDialog
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeSelectableText
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.home.domain.utils.toUiText

@Composable
fun LocationPreferencesDialog(
    isOpen                : Boolean,
    isDonTAskAgainChecked : Boolean = false,
    locationPreference    : LocationPreference = LocationPreference.ALL_WORLD,
    onDismiss             : () -> Unit,
    onDonTAskAgainClick   : (Boolean) -> Unit,
    onOkClick             : (LocationPreference) -> Unit,
    modifier              : Modifier = Modifier
) {
    var currentLocationPreference = rememberSaveable {
        mutableStateOf(locationPreference)
    }
    TrekScapeDialog(
        isOpen = isOpen,
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text  = stringResource(id = R.string.lab_location_preferences),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text  = stringResource(id = R.string.lab_location_preferences_expl),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                val locationPreferences = LocationPreference.entries
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(
                    count = locationPreferences.size,
                    key = { position ->
                        locationPreferences[position].name
                    }
                ) { position ->
                    val isSelected = locationPreferences[position] == currentLocationPreference.value
                    TrekScapeSelectableText(
                        text = locationPreferences[position].toUiText().asString(),
                        isSelected = isSelected,
                        onClick = {
                            currentLocationPreference.value = locationPreferences[position]
                        },
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                }
            }
            TrekScapeCheckboxWithTextOnLeft(
                isChecked = isDonTAskAgainChecked,
                text = stringResource(id = R.string.lab_don_t_ask_again),
                onChecked = onDonTAskAgainClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            TrekScapeActionButton(
                text = stringResource(id = R.string.lab_ok),
                isLoading = false,
                enabled = true,
                onClick = {
                    onOkClick(currentLocationPreference.value)
                },
                modifier = Modifier
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun Smart1ConfirmDialogPreview() {
    TrekScapeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LocationPreferencesDialog(
                isOpen = true,
                isDonTAskAgainChecked = true,
                onDismiss = {  },
                onDonTAskAgainClick = { },
                onOkClick = {  }
            )
        }
    }
}