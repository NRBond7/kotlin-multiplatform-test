package activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import main.MainContract
import main.MainPresenter
import sample.R


class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        activity_main_edittext.handleTextChanged { presenter!!.handleWeightInput(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.menu_main_activity_settings -> {
                    presenter!!.handleSettingsClicked()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    private fun EditText.handleTextChanged(handleTextChanged: (String) -> Unit) =
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                handleTextChanged.invoke(editable.toString())
            }
        })

    override fun openSettings() = startActivity(Intent(this, SettingsActivity::class.java))

    override fun displayWeight() {
        Snackbar.make(activity_main_edittext, "generate weight", Snackbar.LENGTH_LONG).show()
    }

    override fun initPresenter() {
        presenter = MainPresenter()
    }
}