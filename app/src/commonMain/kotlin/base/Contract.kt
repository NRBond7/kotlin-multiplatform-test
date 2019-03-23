package base

import com.russhwolf.settings.Settings

interface Contract {
    interface View {
        // fun displayReadyState()
        // fun displayErrorState()
        // fun displayLoadingState()
        fun getSettingsFactory(): Settings.Factory
        fun logScreenLoad(screenName: String)
        fun logEvent(eventName: String, params: Map<String, String>)
    }

    interface Presenter<V : Contract.View> {

        fun attachView(view: V)

        fun dropView()
    }
}

