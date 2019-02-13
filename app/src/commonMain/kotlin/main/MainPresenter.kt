package main

import base.BasePresenter
import base.Contract


class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter  {

    override fun handleWeightInput(weight: String) = view.displayWeight()

    override fun handleSettingsClicked() = view.openSettings()

}