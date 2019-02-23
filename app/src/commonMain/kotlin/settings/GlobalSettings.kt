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

    val barWeight: DoubleSettingConfig
    val conroyModeEnabled = BooleanSettingConfig(settings, SETTINGS_KEY_CONROY_MODE)
    val initialBarLoad: DoubleSettingConfig
    val metricEnabled = BooleanSettingConfig(settings, SETTINGS_KEY_METRIC)
    val smallestPlateWeight: DoubleSettingConfig

    override val mySettings: List<SettingConfig<*>>
        get() = listOf(
                metricEnabled,
                conroyModeEnabled,
                smallestPlateWeight,
                barWeight,
                initialBarLoad
        )


    init {
        val defaultBarLoad = if (metricEnabled.get()) DEFAULT_BAR_LOAD_KG else DEFAULT_BAR_LOAD_POUNDS
        initialBarLoad = DoubleSettingConfig(settings, SETTINGS_KEY_INITIAL_BAR_LOAD, defaultBarLoad)

        val defaultBarWeight = if (metricEnabled.get()) DEFAULT_BAR_WEIGHT_KG else DEFAULT_BAR_WEIGHT_POUNDS
        barWeight = DoubleSettingConfig(settings, SETTINGS_KEY_BAR_WEIGHT, defaultBarWeight)

        val defaultSmallestWeight = if (metricEnabled.get()) DEFAULT_SMALLEST_WEIGHT_KG else DEFAULT_SMALLEST_WEIGHT_POUNDS
        smallestPlateWeight = DoubleSettingConfig(settings, SETTINGS_KEY_SMALLEST_WEIGHT, defaultSmallestWeight)
    }

}