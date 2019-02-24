package home

import base.Contract

interface HomeContract : Contract {
    interface View : Contract.View {
        fun displayWeight()
        fun getInputWeight(): String
        fun openSettings()
        fun populateWeightField(hint: String, weight: String)
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun handleWeightInput(weight: String)
        fun handleSettingsClicked()
    }
}

