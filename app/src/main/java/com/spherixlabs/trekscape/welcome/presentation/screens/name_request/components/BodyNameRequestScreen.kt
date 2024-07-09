package com.spherixlabs.trekscape.welcome.presentation.screens.name_request.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeFloatingButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeTextField
import com.spherixlabs.trekscape.welcome.presentation.components.WritingTextAnimation
import com.spherixlabs.trekscape.core.presentation.animations.ZoomInOrOutAnimation
import com.spherixlabs.trekscape.welcome.presentation.screens.name_request.NameRequestAction
import com.spherixlabs.trekscape.welcome.presentation.screens.name_request.NameRequestState

@Composable
fun BodyNameRequestScreen(
    modifier : Modifier = Modifier,
    state    : NameRequestState,
    onAction : (NameRequestAction) -> Unit,
){
    Column (
        horizontalAlignment = Alignment.Start,
        modifier            = modifier
            .wrapContentSize()
            .padding(20.dp)
    ){
        Image(
            painter            = painterResource(R.drawable.welcome_image_two),
            contentDescription = "",
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .padding(end = 50.dp, bottom = 20.dp)
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.End)
        )
        Image(
            painter            = painterResource(R.drawable.welcome_image_one),
            contentDescription = "",
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .padding(end = 100.dp)
                .size(170.dp)
                .clip(CircleShape)
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(30.dp))
        WritingTextAnimation(
            text              = stringResource(id = R.string.let_get),
            animationDuration = 500
        )
        WritingTextAnimation(
            text              = stringResource(id = R.string.started),
            animationDuration = 500,
            delayBeforeStart  = 500
        )
        TrekScapeTextField(
            text          = state.name,
            hint          =  stringResource(id = R.string.enter_name),
            onValueChange = { value ->
                onAction(NameRequestAction.OnNameChanged(value))
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        ZoomInOrOutAnimation(show = state.showNext,modifier = Modifier.align(Alignment.End)){
            TrekScapeFloatingButton(
                enable   = state.canGoNext,
                onClick  = { onAction(NameRequestAction.OnNextClicked)},
                content  = { Icon(Icons.AutoMirrored.Rounded.NavigateNext, contentDescription = "TrekScapeFloatingButton")},
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}
//@Preview
//@Composable
//private fun BodyNameRequestScreenPreview(){
//    BodyNameRequestScreen()
//}