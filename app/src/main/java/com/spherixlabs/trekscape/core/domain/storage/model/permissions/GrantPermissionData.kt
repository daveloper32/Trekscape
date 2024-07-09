package com.spherixlabs.trekscape.core.domain.storage.model.permissions

/**
 * [GrantPermissionData] is used to store the permission data.
 *
 * @property isGranted [Boolean] is the permission granted or not.
 * @property permission [String] permission name.
 * @property shouldShowRationale [Boolean] should show the rationale or not.
 * */
data class GrantPermissionData(
    val isGranted           : Boolean,
    val permission          : String,
    val shouldShowRationale : Boolean,
)
