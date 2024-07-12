package com.spherixlabs.trekscape.historical.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * The [HistoricalViewModel] is a core or base View Model that is responsible for handling the
 * control of the historical of the application.
 * */
@HiltViewModel
class HistoricalViewModel @Inject constructor(
    private val userStorage : UserStorage,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [HistoricalState] of the view model.
     * */
    var state by mutableStateOf(HistoricalState())
        private set

    init {
        state = state.copy(
            historicalList = getHistorical(),
        )
    }
    /**
     * This function receives all the possible actions [HistoricalAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [HistoricalAction].
     * */
    fun onAction(
        action : HistoricalAction
    ) {
        when (action) {
            HistoricalAction.OnDismissDetailHistorical -> handleHistoricalItemClicked(null)
            is HistoricalAction.OnHistoricalClicked    -> handleHistoricalItemClicked(action.historicalModel)
        }
    }
    /**
     * This function handles the history click action.
     * */
    private fun handleHistoricalItemClicked(historicalModel: HistoricalModel?) {
        try {
            state = state.copy(
                isShowingDetailHistorical = historicalModel,
            )
        } catch (e: Exception) { Unit }
    }
    /**
     * Returns a list of historical that the user searched.
     *
     * @return List of [HistoricalModel] representing user historical.
     */
    private fun getHistorical() : List<HistoricalModel> = listOf(
        HistoricalModel(
            name     = "beautiful places",
            urlImage = "https://media.cnn.com/api/v1/images/stellar/prod/190417162012-10-earth-beautiful-places.jpg?q=w_3101,h_1744,x_0,y_0,c_fill",
        ),
        HistoricalModel(
            name     = "Eiffel Tower",
            urlImage = "https://www.travelandleisure.com/thmb/9xr8CFGR14sLvR4IhLwKV64fEV0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/TAL-Eiffel-Tower-BESTFRANCE0323-dada0673f8764c099b68d01695ef8057.jpg",
        ),
        HistoricalModel(
            name     = "Taj Mahal",
            urlImage = "https://worldwildschooling.com/wp-content/uploads/2023/11/Taj-Mahal-India_130860698.jpeg",
        ),
        HistoricalModel(
            name     = "Jumm",
            urlImage = "https://media.cnn.com/api/v1/images/stellar/prod/190417163847-26-earth-beautiful-places.jpg?q=w_4000,h_2250,x_0,y_0,c_fill",
        ),
    )
}