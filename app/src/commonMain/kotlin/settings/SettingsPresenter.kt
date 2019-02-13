package settings

import base.BasePresenter

class SettingsPresenter : BasePresenter<SettingsContract.View>(), SettingsContract.Presenter {

    override fun onMetricSettingUpdated(isEnabled: Boolean) {
        view.showMessage("metric on: $isEnabled")
    }

    override fun onBarbellWeightUpdated(weight: String) {
        view.showMessage("new barbell weight: $weight")
    }

    override fun onWeightDenominationUpdated(weight: String) {
        view.showMessage("new min weight: $weight")
    }

    override fun onConroyModeUpdated(isEnabled: Boolean) {
        view.showMessage("conroy mode on: $isEnabled")
    }
}