import UIKit
import app
import MaterialComponents

class ViewController: UIViewController, MainContractView {
    
    @IBOutlet weak var weightField: MDCTextField!
    
    let presenter = MainPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.view = self
//        weightField.text = Proxy().proxyHello()
        weightField.addTarget(self, action: #selector(ViewController.textFieldDidChange(_:)), for: UIControlEvents.editingChanged)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func openSettings() {
        print("Open Settings")
    }
    
    func displayWeight() {
        print("Display Weight")
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        presenter.handleWeightInput(weight: textField.text!)
    }
}
