package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.BodyDetailHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.HeaderDetailHistoricalView

@Composable
fun DetailHistoricalScreenRoot(
    historicalModel: HistoricalModel
) {
    DetailHistoricalScreen(
        historicalModel
    )
}

@Composable
fun DetailHistoricalScreen(historicalModel: HistoricalModel ) {
    AutoFinishBackPressHandler()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderDetailHistoricalView(urlImage = historicalModel.urlImage, missingMeters = historicalModel.missingMeters)
        BodyDetailHistoricalView(name = historicalModel.name, description = historicalModel.description)
    }
}

@Preview
@Composable
private fun HistoricalScreenPreview() {
    TrekScapeTheme {
        DetailHistoricalScreen(
            HistoricalModel(
                name     = "beautiful places",
                urlImage = "https://media.cnn.com/api/v1/images/stellar/prod/190417162012-10-earth-beautiful-places.jpg?q=w_3101,h_1744,x_0,y_0,c_fill",
                missingMeters = "100 km",
                description = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"
            )
        )
    }
}