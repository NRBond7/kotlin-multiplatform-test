package activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import interfaces.View
import kotlinx.android.synthetic.main.activity_main.*
import presenters.MainPresenter
import reporting.AnalyticsHandler
import sample.Platform
import sample.R
import sample.Sample
import sample.transformInput


class MainActivity : AppCompatActivity(), View {

    private val presenter = MainPresenter(this, AnalyticsHandler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)

        Sample().checkMe()
//        textview.text = hello()
        textview.text = transformInput(Platform.name)

        activity_main_raised_button.setOnClickListener { presenter.onRaisedClicked() }
        activity_main_flat_button.setOnClickListener { presenter.onFlatClicked() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
//                R.id.menu_main_activity_settings -> presenter.onSettingsClicked()
                R.id.menu_main_activity_settings -> {
                    showMessage("Settings Clicked")
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun showMessage(message: String) =
        Snackbar.make(findViewById(R.id.activity_main_flat_button), message, Snackbar.LENGTH_LONG).show()
}