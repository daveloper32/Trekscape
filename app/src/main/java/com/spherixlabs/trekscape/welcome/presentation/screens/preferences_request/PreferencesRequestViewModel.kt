package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText
import com.spherixlabs.trekscape.welcome.domain.model.CategoryPreferenceModel
import com.spherixlabs.trekscape.welcome.domain.model.CultureHistory
import com.spherixlabs.trekscape.welcome.domain.model.NatureAdventure
import com.spherixlabs.trekscape.welcome.domain.model.PreferenceModel
import com.spherixlabs.trekscape.welcome.domain.model.Relaxation
import com.spherixlabs.trekscape.welcome.domain.model.extractIds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [PreferencesRequestViewModel] is a core or base View Model that is responsible for handling the
 * control of the preferences request of the application.
 * */
@HiltViewModel
class PreferencesRequestViewModel @Inject constructor(
    private val userStorage : UserStorage,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [PreferencesRequestState] of the view model.
     * */
    var state by mutableStateOf(PreferencesRequestState())
        private set

    /**
     * Private mutable [Channel] that exposes the current events [PreferencesRequestEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<PreferencesRequestEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [PreferencesRequestEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()

    init {
        state = state.copy(
            categories = getCategories(),
        )
    }

    /**
     * This function receives all the possible actions [PreferencesRequestAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [PreferencesRequestAction].
     * */
    fun onAction(
        action : PreferencesRequestAction
    ) {
        when (action) {
            PreferencesRequestAction.OnNextCategoryPreference -> {
                if (state.isShowingLastCategory) {
                    userStorage.preferences = state.preferencesSelected.extractIds()
                    viewModelScope.launch {
                        eventChannel.send(
                            PreferencesRequestEvent.NavigateToHome
                        )
                    }
                    return
                }
                val currentCategory = state.currentCategory + 1
                state = state.copy(
                    currentCategory       = currentCategory,
                    isShowingLastCategory = currentCategory == getCategories().size -1,
                    showButtonBack        = currentCategory != 0,
                    showNext = isSomePreferenceSelectedOnCurrentCategory(
                        currentCategory    = currentCategory,
                        currentPreferences = state.preferencesSelected,
                    ),
                )
            }

            PreferencesRequestAction.OnPreviousCategoryPreference -> {
                val currentCategory = state.currentCategory - 1
                state = state.copy(
                    currentCategory       = currentCategory,
                    isShowingLastCategory = currentCategory == getCategories().size -1,
                    showButtonBack        = currentCategory != 0,
                    showNext = isSomePreferenceSelectedOnCurrentCategory(
                        currentCategory    = currentCategory,
                        currentPreferences = state.preferencesSelected,
                    ),
                )
            }

            is PreferencesRequestAction.OnSelectOrDeselectPreference -> {
                val preferences: MutableList<PreferenceModel> = state.preferencesSelected.toMutableList()
                val preference: PreferenceModel = action.preference
                if (preferences.find { it.id == preference.id } != null) {
                    preferences.remove(preference)
                } else {
                    preferences.add(preference)
                }
                state = state.copy(
                    preferencesSelected = preferences,
                    showNext = isSomePreferenceSelectedOnCurrentCategory(
                        currentCategory    = state.currentCategory,
                        currentPreferences = preferences,
                    ),
                )
            }
        }
    }

    /**
     * Checks if any preference is selected on the current category.
     *
     * @return [Boolean] True if any preference is selected, false otherwise.
     * */
    private fun isSomePreferenceSelectedOnCurrentCategory(
        currentCategory    : Int,
        currentPreferences : List<PreferenceModel>
    ): Boolean {
        return when (currentCategory) {
            0 -> { // NatureAdventure
                currentPreferences.any { it.isNatureAdventure() }
            }
            1 -> { // CultureHistory
                currentPreferences.any { it.isCultureHistory() }
            }
            2 -> { // Relaxation
                currentPreferences.any { it.isRelaxation() }
            }
            else -> false
        }
    }

    /**
     * Returns a list of preference categories that the user can select.
     *
     * @return List of [CategoryPreferenceModel] representing different categories of preferences.
     */
    private fun getCategories() : List<CategoryPreferenceModel> = listOf(
        CategoryPreferenceModel(
            UiText.StringResource(R.string.nature_and_adventure),
            UiText.StringResource(R.string.nature_and_adventure_description),
            NatureAdventure.getValues()),
        CategoryPreferenceModel(
            UiText.StringResource(R.string.culture_and_history),
            UiText.StringResource(R.string.culture_and_history_description),
            CultureHistory.getValues()),
        CategoryPreferenceModel(
            UiText.StringResource(R.string.relaxation_and_wellbeing),
            UiText.StringResource(R.string.relaxation_and_wellbeing_description),
            Relaxation.getValues())
    )
}