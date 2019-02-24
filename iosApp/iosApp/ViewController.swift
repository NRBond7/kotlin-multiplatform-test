import UIKit
import main
import MaterialComponents

class ViewController: UIViewController, HomeContractView {
    
    @IBOutlet weak var weightField: MDCTextField!
    
    let presenter = HomePresenter()
    
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
    
    func getSettingsFactory() -> Multiplatform_settingsSettingsFactory {
        return PlatformFunctionsKt.generateSettingsFactory()
    }
    
    func getInputWeight() -> String {
        return ""
    }
    
    func populateWeightField(hint: String, weight: String) {
        
    }
}
