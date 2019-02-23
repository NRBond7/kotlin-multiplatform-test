package main

import base.Contract

interface MainContract : Contract {
    interface View : Contract.View {
        fun displayWeight()
        fun openSettings()
        fun populateWeightField(weight: String, isMetric: Boolean)
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun handleWeightInput(weight: String)
        fun handleSettingsClicked()
    }
}

