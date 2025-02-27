package io.winapps.journeyapp.models

data class UpdateEntryRequest(
    val id: String,
    val userId: String,
    val username: String,
    val timestamp: String,
    val text: String?,
    val images: List<String>?,
    val locations: List<LocationData>?,
    val tags: List<TagData>?
)

data class UpdateEntryResponse(
    val success: Boolean
)
