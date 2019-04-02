package settings

import base.Contract

interface SettingsContract {

    enum class PickerSetting(val title: String) {
        WEIGHT_UNIT("Weight unit"),
        BAR_WEIGHT("Barbell weight"),
        SMALLEST_PLATE_WEIGHT("Smallest plate weight available")
    }

    interface View : Contract.View {
        fun openDonationScreen()
        fun openEmail(email: String)
        fun populateSettings(email: String, unit: String, barbellTitle: String, barbellWeight: String,
                             plateWeightTitle: String, plateWeight: String, conroyModeEnabled: Boolean)
        fun setConroyMode(conroyModeEnabled: Boolean)
        fun showPickerDialog(title: String, options: List<String>, pickerSetting: PickerSetting)
    }

    interface Presenter : Contract.Presenter<Contract.View> {
        fun onBarbellWeightClicked()
        fun onConroyModeClicked()
        fun onDonateClicked()
        fun onEmailClicked()
        fun onMetricSettingClicked()
        fun onPickerOptionSelected(pickerSetting: PickerSetting, value: String)
        fun onSmallestPlateWeightClicked()
    }
}