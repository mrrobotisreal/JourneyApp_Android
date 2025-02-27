package io.winapps.journeyapp.models

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val displayName: String? = null
)

data class AddLocationRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val locations: List<LocationData>
)

data class AddLocationResponse(
    val success: Boolean
)

data class DeleteLocationRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val locations: List<LocationData>
)

data class DeleteLocationResponse(
    val success: Boolean
)
