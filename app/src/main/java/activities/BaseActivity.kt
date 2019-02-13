package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import base.Contract

abstract class BaseActivity<P : Contract.Presenter<Contract.View>> : AppCompatActivity(), Contract.View {

    var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.dropView()
    }

    abstract fun initPresenter()
}