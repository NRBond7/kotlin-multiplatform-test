package settings

import base.BasePresenter
import base.Contract

class SettingsPresenter : BasePresenter<SettingsContract.View>(), SettingsContract.Presenter {

    lateinit var settings: GlobalSettings

    override fun attachView(view: Contract.View) {
        super.attachView(view)
        settings = GlobalSettings(view.getSettingsFactory())
        updateUI()
    }

    override fun screenName(): String = "Settings"

    override fun onBarbellWeightClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.BAR_WEIGHT)

    override fun onConroyModeClicked() {
        val currentValue = settings.conroyModeEnabled.get()
        val newValue = currentValue.not()
        settings.conroyModeEnabled.set(newValue.toString())
        view.setConroyMode(newValue)
        logSettingClicked("Conroy mode")
        logSettingChanged("Conroy mode", getBooleanText(currentValue), getBooleanText(newValue))
    }

    private fun getBooleanText(on: Boolean) : String = if (on) "on" else "off"

    override fun onMetricSettingClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.WEIGHT_UNIT)

    override fun onPickerOptionSelected(pickerSetting: SettingsContract.PickerSetting, value: String) {
        val oldValue = when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> settings.getWeightUnitString()
            SettingsContract.PickerSetting.BAR_WEIGHT -> settings.barWeight.get().toString()
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> settings.smallestPlateWeight.get().toString()
        }

        when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> {
                val isMetric = value == GlobalSettings.UNIT_KG
                if (isMetric != isMetricUnitOn()) {
                    settings.metricEnabled.set(isMetric.toString())
                    settings.onWeightUnitUpdated(isMetric)
                }
            }
            SettingsContract.PickerSetting.BAR_WEIGHT -> settings.barWeight.set(value)
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> settings.smallestPlateWeight.set(value)
        }
        updateUI()
        if (value != oldValue) logSettingChanged(pickerSetting.title, oldValue, value)
    }

    override fun onSmallestPlateWeightClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT)

    private fun updateUI() {
        view.populateSettings(
                settings.getWeightUnitString(),
                getBarbellTitle(),
                settings.barWeight.get().toString(),
                getPlateWeightTitle(),
                settings.smallestPlateWeight.get().toString(),
                settings.conroyModeEnabled.get()
        )
    }

    private fun handlePickerSettingClicked(pickerSetting: SettingsContract.PickerSetting) {
        val title = when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> SettingsContract.PickerSetting.WEIGHT_UNIT.title
            SettingsContract.PickerSetting.BAR_WEIGHT -> getBarbellTitle()
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> getPlateWeightTitle()
        }

        val pickerOptions = when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> settings.getWeightUnitOptions()
            SettingsContract.PickerSetting.BAR_WEIGHT -> settings.getBarbellWeightOptions()
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> settings.getSmallestPlateOptions()
        }

        logSettingClicked(pickerSetting.title)
        view.showPickerDialog(title, pickerOptions, pickerSetting)
    }

    private fun logSettingClicked(settingName: String) {
        val params = HashMap<String, String>()
        params["setting"] = settingName
        view.logEvent("setting_clicked", params)
    }

    private fun logSettingChanged(settingName: String, oldValue: String, newValue: String) {
        val params = HashMap<String, String>()
        params["setting"] = settingName
        params["old_value"] = oldValue
        params["new_value"] = newValue
        view.logEvent("setting_changed", params)
    }

    private fun isMetricUnitOn(): Boolean = settings.metricEnabled.get()

    private fun getBarbellTitle(): String = "${SettingsContract.PickerSetting.BAR_WEIGHT.title} (${settings.getWeightUnitString()})"

    private fun getPlateWeightTitle(): String = "${SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT.title} (${settings.getWeightUnitString()})"
}