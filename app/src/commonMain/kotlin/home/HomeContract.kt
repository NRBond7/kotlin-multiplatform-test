package home

import base.Contract
import lib.Plate

interface HomeContract : Contract {

    interface View : Contract.View {
        fun displayWeight(plates: List<Plate>)
        fun getInputWeight(): String
        fun openSettings()
        fun populateWeightField(hint: String, weight: String)
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun onWeightInput(weight: String)
        fun handleSettingsClicked()
    }
}

