package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText
import com.spherixlabs.trekscape.welcome.presentation.domain.models.CategoryPreferenceModel
import com.spherixlabs.trekscape.welcome.presentation.domain.models.CultureHistory
import com.spherixlabs.trekscape.welcome.presentation.domain.models.NatureAdventure
import com.spherixlabs.trekscape.welcome.presentation.domain.models.Relaxation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * The [PreferencesRequestViewModel] is a core or base View Model that is responsible for handling the
 * control of the preferences request of the application.
 * */
@HiltViewModel
class PreferencesRequestViewModel @Inject constructor(
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [PreferencesRequestState] of the view model.
     * */
    var state by mutableStateOf(PreferencesRequestState())
        private set


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
                if (state.isShowingLastCategory) return
                val currentCategory = state.currentCategory + 1
                state = state.copy(
                    currentCategory       = currentCategory,
                    isShowingLastCategory = currentCategory == getCategories().size -1,
                    showButtonBack        = currentCategory != 0
                )
            }

            PreferencesRequestAction.OnPreviousCategoryPreference -> {
                val currentCategory = state.currentCategory - 1
                state = state.copy(
                    currentCategory       = currentCategory,
                    isShowingLastCategory = currentCategory == getCategories().size -1,
                    showButtonBack        = currentCategory != 0
                )
            }

            is PreferencesRequestAction.OnSelectOrDeselectPreference -> {
                val preferences = state.preferencesSelected.toMutableList()
                val preference  = action.preference
                if(preferences.contains(preference)){
                    preferences.remove(preference)
                }else{
                    preferences.add(preference)
                }
                state = state.copy(
                    preferencesSelected = preferences
                )
            }
        }
    }
    /**
     * Returns a list of preference categories that the user can select.
     *
     * @return List of [CategoryPreferenceModel] representing different categories of preferences.
     */
    fun getCategories() : List<CategoryPreferenceModel> = listOf(
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