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
        private const val DEFAULT_SMALLEST_WEIGHT_KG = 1.0
        private const val DEFAULT_SMALLEST_WEIGHT_POUNDS = 2.5

        const val UNIT_LBS = "lbs"
        const val UNIT_KG = "kg"

        private val OPTIONS_WEIGHT_UNIT = listOf(UNIT_LBS, UNIT_KG)
        private val OPTIONS_BAR_WEIGHT_KG = listOf("15.0", DEFAULT_BAR_WEIGHT_KG.toString())
        private val OPTIONS_BAR_WEIGHT_LBS = listOf("35.0", DEFAULT_BAR_WEIGHT_POUNDS.toString())
        private val OPTIONS_SMALLEST_PLATE_WEIGHT_KG = listOf(DEFAULT_SMALLEST_WEIGHT_KG.toString(), "1.25", "2.0", "2.5")
        private val OPTIONS_SMALLEST_PLATE_WEIGHT_LBS = listOf(DEFAULT_SMALLEST_WEIGHT_POUNDS.toString(), "5.0")
    }

    val barWeight: DoubleSettingConfig
    val conroyModeEnabled = BooleanSettingConfig(settings, SETTINGS_KEY_CONROY_MODE)
    val initialBarLoad: DoubleSettingConfig
    val metricEnabled = BooleanSettingConfig(settings, SETTINGS_KEY_METRIC)
    val smallestPlateWeight: DoubleSettingConfig

    override val mySettings: List<SettingConfig<*>>
        get() = listOf(
                barWeight,
                conroyModeEnabled,
                initialBarLoad,
                metricEnabled,
                smallestPlateWeight
        )

    init {
        val defaultBarLoad = if (metricEnabled.get()) DEFAULT_BAR_LOAD_KG else DEFAULT_BAR_LOAD_POUNDS
        initialBarLoad = DoubleSettingConfig(settings, SETTINGS_KEY_INITIAL_BAR_LOAD, defaultBarLoad)

        val defaultBarWeight = if (metricEnabled.get()) DEFAULT_BAR_WEIGHT_KG else DEFAULT_BAR_WEIGHT_POUNDS
        barWeight = DoubleSettingConfig(settings, SETTINGS_KEY_BAR_WEIGHT, defaultBarWeight)

        val defaultSmallestWeight = if (metricEnabled.get()) DEFAULT_SMALLEST_WEIGHT_KG else DEFAULT_SMALLEST_WEIGHT_POUNDS
        smallestPlateWeight = DoubleSettingConfig(settings, SETTINGS_KEY_SMALLEST_WEIGHT, defaultSmallestWeight)
    }

    fun getWeightUnitString() = if (metricEnabled.get()) UNIT_KG else UNIT_LBS

    fun getWeightUnitOptions() = OPTIONS_WEIGHT_UNIT

    fun getBarbellWeightOptions() = if (metricEnabled.get()) OPTIONS_BAR_WEIGHT_KG else OPTIONS_BAR_WEIGHT_LBS

    fun getSmallestPlateOptions() = if (metricEnabled.get()) OPTIONS_SMALLEST_PLATE_WEIGHT_KG else OPTIONS_SMALLEST_PLATE_WEIGHT_LBS

    fun onWeightUnitUpdated(isMetric: Boolean) {
        val defaultBarLoad = if (isMetric) DEFAULT_BAR_LOAD_KG else DEFAULT_BAR_LOAD_POUNDS
        initialBarLoad.set(defaultBarLoad.toString())

        val defaultBarWeight = if (isMetric) DEFAULT_BAR_WEIGHT_KG else DEFAULT_BAR_WEIGHT_POUNDS
        barWeight.set(defaultBarWeight.toString())

        val defaultSmallestWeight = if (isMetric) DEFAULT_SMALLEST_WEIGHT_KG else DEFAULT_SMALLEST_WEIGHT_POUNDS
        smallestPlateWeight.set(defaultSmallestWeight.toString())
    }

}