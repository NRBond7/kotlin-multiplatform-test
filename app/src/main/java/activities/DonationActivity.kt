package activities

import android.os.Bundle
import donation.DonationContract
import donation.DonationPresenter
import kotlinx.android.synthetic.main.activity_donation.*
import sample.R

class DonationActivity : BaseActivity<DonationPresenter>(), DonationContract.View {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_donation)
         activity_donation_toolbar.setNavigationOnClickListener { onBackPressed() }
     }

    override fun initPresenter() {
        presenter = DonationPresenter()
    }
}