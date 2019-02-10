package reporting

import android.util.Log

actual class AnalyticsHandler {
    actual fun logEvent(event: String) {
        Log.d("AnalyticsHandler", event)
    }

    actual fun logScreen(screenName: String) {
        Log.d("AnalyticsHandler", screenName)
    }
}