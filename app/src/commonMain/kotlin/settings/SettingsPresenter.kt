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

    override fun onBarbellWeightClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.BAR_WEIGHT)

    override fun onConroyModeClicked() {
        val enableOrDisable = settings.conroyModeEnabled.get().not()
        settings.conroyModeEnabled.set(enableOrDisable.toString())
        view.setConroyMode(enableOrDisable)
    }

    override fun onMetricSettingClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.WEIGHT_UNIT)

    override fun onPickerOptionSelected(pickerSetting: SettingsContract.PickerSetting, value: String) {
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
    }

    override fun onSmallestPlateWeightClicked() = handlePickerSettingClicked(SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT)

    private fun updateUI() {
        view.populateSettings(
                getUnitString(),
                settings.barWeight.get().toString(),
                settings.smallestPlateWeight.get().toString(),
                settings.conroyModeEnabled.get()
        )
    }

    private fun handlePickerSettingClicked(pickerSetting: SettingsContract.PickerSetting) {
        val title = when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> SettingsContract.PickerSetting.WEIGHT_UNIT.title
            SettingsContract.PickerSetting.BAR_WEIGHT -> "${SettingsContract.PickerSetting.BAR_WEIGHT.title} (${getUnitString()})"
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> "${SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT.title} (${getUnitString()})"
        }

        val pickerOptions = when (pickerSetting) {
            SettingsContract.PickerSetting.WEIGHT_UNIT -> GlobalSettings.OPTIONS_WEIGHT_UNIT
            SettingsContract.PickerSetting.BAR_WEIGHT -> if (isMetricUnitOn()) GlobalSettings.OPTIONS_BAR_WEIGHT_KG else GlobalSettings.OPTIONS_BAR_WEIGHT_LBS
            SettingsContract.PickerSetting.SMALLEST_PLATE_WEIGHT -> if (isMetricUnitOn()) GlobalSettings.OPTIONS_SMALLEST_PLATE_WEIGHT_KG else GlobalSettings.OPTIONS_SMALLEST_PLATE_WEIGHT_LBS
        }

        view.showPickerDialog(title, pickerOptions, pickerSetting)
    }

    private fun getUnitString(): String = if (isMetricUnitOn()) GlobalSettings.UNIT_KG else GlobalSettings.UNIT_LBS

    private fun isMetricUnitOn(): Boolean = settings.metricEnabled.get()
}