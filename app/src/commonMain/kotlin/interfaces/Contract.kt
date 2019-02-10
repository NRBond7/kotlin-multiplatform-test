package interfaces

import reporting.AnalyticsHandler

interface View {
    fun showMessage(message: String)
    // fun displayReadyState()
    // fun displayErrorState()
    // fun displayLoadingState()
}

interface Presenter {
    val view: View
    val analyticsHandler: AnalyticsHandler

    // Since this is the root presenter, it wouldn't have any screen-specific callbacks in a multiscreen app
    // Rather, it would have generic state handling that all screens should have
    // onViewLoaded
    // onViewDetached
    // etc
    fun onRaisedClicked()
    fun onFlatClicked()
}