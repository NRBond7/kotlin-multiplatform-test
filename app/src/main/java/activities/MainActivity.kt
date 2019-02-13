package activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.TextView
import interfaces.View
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
        setSupportActionBar(findViewById(R.id.activity_main_toolbar))

        Sample().checkMe()
        val textView: TextView = findViewById(R.id.textview)
//        textView.text = hello()
        textView.text = transformInput(Platform.name)

        val raisedButton: Button = findViewById(R.id.activity_main_raised)
        val flatButton: Button = findViewById(R.id.activity_main_flat)

        raisedButton.setOnClickListener { presenter.onRaisedClicked() }
        flatButton.setOnClickListener { presenter.onFlatClicked() }
    }

    override fun showMessage(message: String) =
        Snackbar.make(findViewById(R.id.activity_main_flat), message, Snackbar.LENGTH_LONG).show()
}