package home

import base.BasePresenter
import base.Contract
import settings.GlobalSettings


class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter  {

    lateinit var settings: GlobalSettings

    override fun attachView(view: Contract.View) {
        super.attachView(view)
        settings = GlobalSettings(view.getSettingsFactory())
        updateUI()
    }

    override fun dropView() {
        settings.initialBarLoad.set(view.getInputWeight())
        super.dropView()
    }

    override fun handleWeightInput(weight: String) = view.displayWeight()

    override fun handleSettingsClicked() = view.openSettings()

    private fun updateUI() {
        view.populateWeightField(
                "Weight (${settings.getWeightUnitString()})",
                settings.initialBarLoad.get().toString()
        )
    }

}