package settings

import com.russhwolf.settings.Settings
import lib.*

class GlobalSettings(factory: Settings.Factory) : SettingsRepository(factory, SETTINGS_NAMESPACE_GLOBAL) {

    companion object {
        private const val SETTINGS_NAMESPACE_GLOBAL = "GLOBAL"
        private const val SETTINGS_KEY_BAR_WEIGHT = "BAR_WEIGHT"
        private const val SETTINGS_KEY_CONROY_MODE = "CONROY_MODE"
        private const val SETTINGS_KEY_INITIAL_BAR_LOAD = "BAR_LOAD"
        private const val SETTINGS_KEY_METRIC = "METRIC_WEIGHTS"
        private const val SETTINGS_KEY_SMALLEST_WEIGHT = "SMALLEST_WEIGHT"
        private const val DEFAULT_BAR_LOAD_KG = 100.0
        private const val DEFAULT_BAR_LOAD_POUNDS = 225.0
        private const val DEFAULT_BAR_WEIGHT_KG = 20.0
        private const val DEFAULT_BAR_WEIGHT_POUNDS = 45.0
        private const val DEFAULT_SMALLEST_WEIGHT_KG = 0.5
        private const val DEFAULT_SMALLEST_WEIGHT_POUNDS = 2.5
    }

    val barWeightSetting: DoubleSettingConfig
    val conroyModeSetting = BooleanSettingConfig(settings, SETTINGS_KEY_CONROY_MODE)
    val initialBarWeightSetting: DoubleSettingConfig
    val metricSetting = BooleanSettingConfig(settings, SETTINGS_KEY_METRIC)
    val smallestWeightSetting: DoubleSettingConfig

    override val mySettings: List<SettingConfig<*>>
        get() = listOf(
                metricSetting,
                conroyModeSetting,
                smallestWeightSetting,
                barWeightSetting,
                initialBarWeightSetting
        )


    init {
        val defaultBarLoad = if (metricSetting.get()) DEFAULT_BAR_LOAD_KG else DEFAULT_BAR_LOAD_POUNDS
        initialBarWeightSetting = DoubleSettingConfig(settings, SETTINGS_KEY_INITIAL_BAR_LOAD, defaultBarLoad)

        val defaultBarWeight = if (metricSetting.get()) DEFAULT_BAR_WEIGHT_KG else DEFAULT_BAR_WEIGHT_POUNDS
        barWeightSetting = DoubleSettingConfig(settings, SETTINGS_KEY_BAR_WEIGHT, defaultBarWeight)

        val defaultSmallestWeight = if (metricSetting.get()) DEFAULT_SMALLEST_WEIGHT_KG else DEFAULT_SMALLEST_WEIGHT_POUNDS
        smallestWeightSetting = DoubleSettingConfig(settings, SETTINGS_KEY_SMALLEST_WEIGHT, defaultSmallestWeight)
    }

}