package main

import base.Contract

interface MainContract : Contract {
    interface View : Contract.View {
        fun openSettings()
        fun displayWeight()
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun handleWeightInput(weight: String)
        fun handleSettingsClicked()
    }
}

