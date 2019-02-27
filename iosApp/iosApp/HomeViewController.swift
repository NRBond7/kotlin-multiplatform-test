import UIKit
import main
import MaterialComponents

class HomeViewController: BaseViewController<HomeContractPresenter>, HomeContractView {
    
    @IBOutlet weak var weightLabel: UILabel!
    @IBOutlet weak var weightField: MDCTextField!
    @IBOutlet weak var plateDebugText: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        weightField.addTarget(self, action: #selector(HomeViewController.textFieldDidChange(_:)), for: UIControlEvents.editingChanged)
        presenter?.onWeightInput(weight: getInputWeight())
    }
    
    override func initPresenter() {
        presenter = HomePresenter()
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        presenter?.onWeightInput(weight: textField.text!)
    }
    
    @IBAction func onSettingsClicked(_ sender: Any) {
        presenter?.handleSettingsClicked()
    }
    
    func getInputWeight() -> String {
        return weightField.text ?? ""
    }
    
    func openSettings() {
        print("open settings")
    }
    
    func populateWeightField(hint: String, weight: String) {
        weightLabel.text = hint
        weightField.text = weight
    }
    
    func displayWeight(plates: [Plate]) {
        print("DisplayWeight")
        print(plates)
        plateDebugText.text = plates.description
    }
}
