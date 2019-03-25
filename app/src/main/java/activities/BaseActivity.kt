package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import base.Contract
import com.google.firebase.analytics.FirebaseAnalytics
import com.russhwolf.settings.Settings
import lib.generateSettingsFactory

abstract class BaseActivity<P : Contract.Presenter<Contract.View>> : AppCompatActivity(), Contract.View {

    var presenter: P? = null

    lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        analytics = FirebaseAnalytics.getInstance(this)
    }

    override fun onResume() {
        super.onResume()
        presenter!!.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter!!.dropView()
    }

    abstract fun initPresenter()

    override fun getSettingsFactory(): Settings.Factory = generateSettingsFactory(this)

    override fun logScreenLoad(screenName: String) =
            analytics.setCurrentScreen(this, screenName, null)

    override fun logEvent(eventName: String, params: Map<String, String>) {
        val bundle = Bundle()
        params.keys.forEach {
            bundle.putString(it, params[it])
        }
        analytics.logEvent(eventName, bundle)
    }
}