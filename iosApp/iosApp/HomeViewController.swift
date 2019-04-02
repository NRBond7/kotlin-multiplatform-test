import UIKit
import main
import MaterialComponents
import Crashlytics

class HomeViewController: BaseViewController<HomeContractPresenter>, HomeContractView, UITextFieldDelegate {
    
    private static let characterLimit = 5
    
    @IBOutlet weak var weightLabel: UILabel!
    @IBOutlet weak var weightField: MDCTextField!
    @IBOutlet weak var errorLabel: UILabel!
    @IBOutlet weak var plateView: BarbellPlatesView!
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        weightField.addTarget(self, action: #selector(HomeViewController.textFieldDidChange(_:)), for: UIControl.Event.editingChanged
        )
        weightField.delegate = self
        
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
        let viewController:UIViewController = UIStoryboard(name: "Settings", bundle: nil).instantiateViewController(withIdentifier: "Settings") as! SettingsViewController
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    func populateWeightField(hint: String, weight: String) {
        weightLabel.text = hint
        weightField.text = weight
    }
    
    func displayWeight(plates: [Plate], errorText: String) {
        plateView.updatePlates(plates: plates)
        errorLabel.text = errorText.isEmpty ? " " : errorText
        if (errorText.isEmpty) {
            weightLabel.textColor = UIColor(named: "ColorSecondary")
        } else {
            weightLabel.textColor = .red
        }
    }
    
    @objc func doneWithNumberPad() {
        weightField.resignFirstResponder()
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let currentText = textField.text ?? ""
        guard let stringRange = Range(range, in: currentText) else { return false }
        
        let updatedText = currentText.replacingCharacters(in: stringRange, with: string)
        
        return updatedText.count <= HomeViewController.characterLimit
    }
}
