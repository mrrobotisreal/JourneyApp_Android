package io.winapps.journeyapp.models

data class DeleteEntryRequest(
    val userId: String,
    val timestamp: String
)

data class DeleteEntryResponse(
    val success: Boolean
)
