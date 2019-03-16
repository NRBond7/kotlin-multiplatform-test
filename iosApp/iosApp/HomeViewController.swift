import UIKit
import main
import MaterialComponents

class HomeViewController: BaseViewController<HomeContractPresenter>, HomeContractView {
    
    @IBOutlet weak var weightLabel: UILabel!
    @IBOutlet weak var weightField: MDCTextField!
    @IBOutlet weak var plateView: BarbellPlatesView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        weightField.addTarget(self, action: #selector(HomeViewController.textFieldDidChange(_:)), for: UIControlEvents.editingChanged
        )
        
        let toolbar = UIToolbar(frame:CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 50))
        toolbar.barStyle = .default
        toolbar.items = [
            UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil),
            UIBarButtonItem(title: "Done", style: .plain, target: self, action: #selector(doneWithNumberPad))]
        toolbar.sizeToFit()
        weightField.inputAccessoryView = toolbar
        
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
        plateView.updatePlates(plates: plates)
    }
    
    @objc func doneWithNumberPad() {
        weightField.resignFirstResponder()
    }
}
