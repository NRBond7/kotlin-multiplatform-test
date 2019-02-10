package reporting

expect class AnalyticsHandler {
    fun logEvent(event: String)
    fun logScreen(screenName: String)
}