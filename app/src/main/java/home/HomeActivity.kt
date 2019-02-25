package home

import activities.BaseActivity
import activities.SettingsActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_home.*
import lib.Plate
import sample.R


class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(activity_home_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        activity_home_edittext.handleTextChanged { presenter!!.onWeightInput(it) }
        activity_home_plate_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

    private fun EditText.handleTextChanged(handleTextChanged: (String) -> Unit) {
        this.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                handleTextChanged.invoke(editable.toString())
            }
        })
    }

    override fun openSettings() = startActivity(Intent(this, SettingsActivity::class.java))

    override fun displayWeight(plates: List<Plate>) {
        activity_home_plate_list.adapter = PlateAdapter(plates)
    }

    override fun initPresenter() {
        presenter = HomePresenter()
    }

    override fun getInputWeight(): String = activity_home_edittext.text.toString()

    override fun populateWeightField(hint: String, weight: String) {
        activity_home_text_input_layout.hint = hint
        activity_home_edittext.setText(weight)
    }
}