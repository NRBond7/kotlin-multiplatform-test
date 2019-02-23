package base

import com.russhwolf.settings.Settings

interface Contract {
    interface View {
        // fun displayReadyState()
        // fun displayErrorState()
        // fun displayLoadingState()
        fun getSettingsFactory(): Settings.Factory
    }

    interface Presenter<V : Contract.View> {

        fun attachView(view: V)

        fun dropView()
    }
}

