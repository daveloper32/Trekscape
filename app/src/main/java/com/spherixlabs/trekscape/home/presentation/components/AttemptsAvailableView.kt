package com.spherixlabs.trekscape.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.animations.JumpingInfiniteAnimation

@Composable
fun AttemptsAvailableView(attempts : Int) {
    JumpingInfiniteAnimation {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier          = Modifier
                .clip(CircleShape)
                .background(colorResource(
                    id = when(attempts){
                        5    -> R.color.five_attempts
                        4    -> R.color.four_attempts
                        3    -> R.color.three_attempts
                        2    -> R.color.two_attempts
                        1    -> R.color.one_attempt
                        else -> R.color.zero_attempts
                    }
                ))
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Text(
                text  = stringResource(id = R.string.lab_attempts),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text  = "$attempts",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}
@Preview
@Composable
fun AttemptsAvailableViewPreview() {
    AttemptsAvailableView(attempts = 3)
}