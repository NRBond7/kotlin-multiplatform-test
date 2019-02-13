package settings

import base.Contract

interface SettingsContract {
    interface View : Contract.View{
        fun showMessage(message: String)
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun onMetricSettingUpdated(isEnabled: Boolean)
        fun onBarbellWeightUpdated(weight: String)
        fun onWeightDenominationUpdated(weight: String)
        fun onConroyModeUpdated(isEnabled: Boolean)
    }
}