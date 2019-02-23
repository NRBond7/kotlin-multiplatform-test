package main

import base.BasePresenter
import base.Contract
import settings.GlobalSettings


class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter  {

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
        view.populateWeightField(settings.initialBarLoad.get().toString(), settings.metricEnabled.get())
    }

}