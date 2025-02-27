package io.winapps.journeyapp.models

data class TagData(
    val key: String,
    val value: String? = null
)

data class AddTagRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val tags: List<TagData>
)

data class AddTagResponse(
    val success: Boolean
)

data class DeleteTagRequest(
    val username: String,
    val userId: String,
    val timestamp: String,
    val entryId: String,
    val tags: List<TagData>
)

data class DeleteTagResponse(
    val success: Boolean
)
