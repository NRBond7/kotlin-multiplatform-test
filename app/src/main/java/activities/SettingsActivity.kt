package activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_settings.*
import sample.R
import settings.SettingsContract
import settings.SettingsPresenter

class SettingsActivity : BaseActivity<SettingsPresenter>(), SettingsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        activity_settings_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun initPresenter() {
        presenter = SettingsPresenter()
    }

    override fun showMessage(message: String) {
        Snackbar.make(activity_settings_toolbar, message, Snackbar.LENGTH_LONG).show()
    }
}