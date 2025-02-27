package io.winapps.journeyapp.models

data class CreateNewEntryRequest(
    val userId: String,
    val username: String,
    val text: String,
    val timestamp: String,
    val images: List<String>?,
    val locations: List<LocationData>?,
    val tags: List<TagData>?
)

data class CreateNewEntryResponse(
    val id: String,
    val userId: String,
    val username: String,
    val text: String,
    val timestamp: String,
    val lastUpdated: String,
    val images: List<String>?,
    val locations: List<LocationData>?,
    val tags: List<TagData>?
)
