package com.spherixlabs.trekscape.core.presentation

/**
 * Describe the state [MainState] of the main.
 *
 * @property isBasicInfoFilled [Boolean] Indicates whether the user's basic information is filled or not.
 * @property isCheckingIfUserBasicInfoIsAlreadyFilled [Boolean] Indicates that the basic user information is being checked or not.
 * */
data class MainState(
    val isBasicInfoFilled                        : Boolean = false,
    val isCheckingIfUserBasicInfoIsAlreadyFilled : Boolean = false,
)
