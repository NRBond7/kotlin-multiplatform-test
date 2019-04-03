import UIKit
import main

class PatronageViewController: BaseViewController<DonationPresenter> {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func initPresenter() {
        presenter = DonationPresenter()
    }
}
