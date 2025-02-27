package io.winapps.journeyapp.models

data class AddImageRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val images: List<String>
)

data class AddImageResponse(
    val success: Boolean
)

data class DeleteImageRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val images: List<String>,
    val imageToDelete: String
)

data class DeleteImageResponse(
    val success: Boolean
)

data class UploadImagesReturns(
    val success: Boolean,
    val imageURLs: List<String>
)
