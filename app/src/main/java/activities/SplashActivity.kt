package activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import home.HomeActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }
}