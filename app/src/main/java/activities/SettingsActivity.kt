package activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.selector
import sample.R
import settings.SettingsContract
import settings.SettingsPresenter

class SettingsActivity : BaseActivity<SettingsPresenter>(), SettingsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        activity_settings_toolbar.setNavigationOnClickListener { onBackPressed() }
        activity_settings_metric_container.setOnClickListener { presenter!!.onMetricSettingClicked() }
        activity_settings_bar_weight_container.setOnClickListener { presenter!!.onBarbellWeightClicked() }
        activity_settings_smallest_plate_weight_container.setOnClickListener { presenter!!.onSmallestPlateWeightClicked() }
        activity_settings_conroy_mode_container.setOnClickListener { presenter!!.onConroyModeClicked() }
        activity_settings_conroy_mode_switch.setOnClickListener { presenter!!.onConroyModeClicked() }
    }

    override fun initPresenter() {
        presenter = SettingsPresenter()
    }

    override fun populateSettings(unit: String, barbellTitle: String, barbellWeight: String,
                                  plateWeightTitle: String, plateWeight: String, conroyModeEnabled: Boolean) {

        activity_settings_weight_unit_value.text = unit
        activity_settings_bar_weight_title.text = barbellTitle
        activity_settings_bar_weight_value.text = barbellWeight
        activity_settings_smallest_plate_title.text = plateWeightTitle
        activity_settings_smallest_plate_weight_value.text = plateWeight
        activity_settings_conroy_mode_switch.isChecked = conroyModeEnabled
    }

    override fun setConroyMode(conroyModeEnabled: Boolean) {
        activity_settings_conroy_mode_switch.isChecked = conroyModeEnabled
    }

    override fun showPickerDialog(title: String, options: List<String>, pickerSetting: SettingsContract.PickerSetting) =
            selector(title, options) { _, i ->
                presenter!!.onPickerOptionSelected(pickerSetting, options[i])
            }
}