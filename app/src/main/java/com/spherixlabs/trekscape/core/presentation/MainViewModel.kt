package com.spherixlabs.trekscape.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [MainViewModel] is a core or base View Model that is responsible for handling the
 * control of the main screen of the application.
 * */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userStorage : UserStorage
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isCheckingIfUserBasicInfoIsAlreadyFilled = true,
            )
            state = state.copy(
                isBasicInfoFilled = isUserBasicInfoAlreadyFilled(),
            )
            state = state.copy(
                isCheckingIfUserBasicInfoIsAlreadyFilled = false,
            )
        }
    }

    /**
     * This function checks if the user's basic information is already filled.
     *
     * @return [Boolean] True if the user's basic information is already filled, false otherwise.
     * */
    private fun isUserBasicInfoAlreadyFilled(): Boolean =
        userStorage.name.isNotEmpty() &&
        userStorage.preferences.isNotEmpty()
}