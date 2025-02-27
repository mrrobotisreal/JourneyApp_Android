package io.winapps.journeyapp.models

enum class SortRule(val id: String) {
    Newest("Newest"),
    Oldest("Oldest")
}

enum class Timeframe(val id: String) {
    All("All time"),
    Past30Days("Past 30 days"),
    Past3Months("Past 3 months"),
    Past6Months("Past 6 months"),
    PastYear("Past year"),
    CustomRange("Select a timeframe")
}

data class FilterOptions(
    val selectedLocations: Set<LocationData> = emptySet(),
    val selectedTags: Set<TagData> = emptySet(),
    val sortRule: SortRule = SortRule.Newest,
    val timeframe: Timeframe = Timeframe.All,
    val fromDate: String? = null,
    val toDate: String? = null
)

data class SearchEntriesBody(
    val searchQuery: String,
    val locations: List<LocationData>,
    val tags: List<TagData>,
    val sortRule: String,
    val timeframe: String,
    val fromDate: String?,
    val toDate: String?
)
