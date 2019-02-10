package presenters

import interfaces.Presenter
import interfaces.View
import reporting.AnalyticsHandler


class MainPresenter(override val view: View, override val analyticsHandler: AnalyticsHandler) : Presenter {

    init {
        analyticsHandler.logScreen("Main")
    }

    override fun onRaisedClicked() {
        analyticsHandler.logEvent("click:raise")
        view.showMessage("Raised clicked")
    }

    override fun onFlatClicked() {
        analyticsHandler.logEvent("click:flat")
        view.showMessage("flat clicked")
    }
}