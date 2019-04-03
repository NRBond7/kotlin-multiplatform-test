import UIKit
import main
import MaterialComponents

class SettingsViewController: BaseViewController<SettingsContractPresenter>, SettingsContractView, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var patronageIcon: UIImageView!
    @IBOutlet weak var emailText: UILabel!
    @IBOutlet weak var weightUnitValue: UITextView!
    @IBOutlet weak var barbwellWeightHeader: UILabel!
    @IBOutlet weak var barbellWeightValue: UITextView!
    @IBOutlet weak var smallestPlateWeightHeader: UILabel!
    @IBOutlet weak var smallestPlateValue: UITextView!
    @IBOutlet weak var conroyModeSwitch: UISwitch!
    
    private let picker: UIPickerView = UIPickerView()

    private var currentSetting: SettingsContractPickerSetting? = nil
    private var options: [String] = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        patronageIcon.tintColor = UIColor(named: "ColorSecondary")
        
        picker.showsSelectionIndicator = true
        picker.delegate = self
        picker.dataSource = self
        picker.backgroundColor = .white
        
        let toolbar = UIToolbar()
        toolbar.barStyle = .default
        toolbar.sizeToFit()
        toolbar.isTranslucent = true
        toolbar.tintColor = UIColor(named: "ColorSecondary")
        toolbar.isUserInteractionEnabled = true
        toolbar.items = [
            UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil),
            UIBarButtonItem(title: "Done", style: .plain, target: self, action: #selector(SettingsViewController.onPickerValueSelected))
        ]
        
        weightUnitValue.inputView = picker
        barbellWeightValue.inputView = picker
        smallestPlateValue.inputView = picker
        weightUnitValue.inputAccessoryView = toolbar
        barbellWeightValue.inputAccessoryView = toolbar
        smallestPlateValue.inputAccessoryView = toolbar
    }
    
    override func initPresenter() {
        presenter = SettingsPresenter()
    }
    
    func populateSettings(email: String, unit: String, barbellTitle: String, barbellWeight: String, plateWeightTitle: String, plateWeight: String, conroyModeEnabled: Bool) {
        emailText.text = email
        weightUnitValue.text = unit
        barbwellWeightHeader.text = barbellTitle
        barbellWeightValue.text = barbellWeight
        smallestPlateWeightHeader.text = plateWeightTitle
        smallestPlateValue.text = plateWeight
        conroyModeSwitch.isOn = conroyModeEnabled
    }
    
    func setConroyMode(conroyModeEnabled: Bool) {
        conroyModeSwitch.isOn = conroyModeEnabled
    }
    
    func openDonationScreen() {
        let viewController:UIViewController = UIStoryboard(name: "Patronage", bundle: nil).instantiateViewController(withIdentifier: "Patronage") as! PatronageViewController
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    func openEmail(email: String) {
        let pasteboard = UIPasteboard.general
        pasteboard.string = email
        let message = MDCSnackbarMessage()
        message.text = "Email copied to clipboard"
        MDCSnackbarManager.show(message)
    }
    
    func showPickerDialog(title: String, options: [String], pickerSetting: SettingsContractPickerSetting) {
        self.options = options
        self.currentSetting = pickerSetting
        
        var index = 0
        if weightUnitValue.isFirstResponder {
            index = options.firstIndex(of: weightUnitValue.text)!
        } else if barbellWeightValue.isFirstResponder {
            index = options.firstIndex(of: barbellWeightValue.text)!
        } else if smallestPlateValue.isFirstResponder {
            index = options.firstIndex(of: smallestPlateValue.text)!
        }
        
        picker.reloadAllComponents()
        picker.selectRow(index, inComponent: 0, animated: false)
    }
    
    @IBAction func onContactClicked(_ sender: Any) {
        presenter!.onEmailClicked()
    }
    
    @IBAction func onPatronageClicked(_ sender: Any) {
        presenter!.onDonateClicked()
    }
    
    @IBAction func onWeightUnitClicked(_ sender: Any) {
        weightUnitValue.becomeFirstResponder()
        presenter!.onMetricSettingClicked()
    }
    
    @IBAction func onBarbellWeightClicked(_ sender: Any) {
        barbellWeightValue.becomeFirstResponder()
        presenter!.onBarbellWeightClicked()
    }
    
    @IBAction func onSmallestPlateClicked(_ sender: Any) {
        smallestPlateValue.becomeFirstResponder()
        presenter!.onSmallestPlateWeightClicked()
    }
    
    @IBAction func onConroyModeSwitchToggled(_ sender: Any) {
        presenter!.onConroyModeClicked()
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return options.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return options[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        presenter!.onPickerOptionSelected(pickerSetting: self.currentSetting!, value: options[row])
    }
    
    @objc func onPickerValueSelected() {
        weightUnitValue.resignFirstResponder()
        barbellWeightValue.resignFirstResponder()
        smallestPlateValue.resignFirstResponder()
    }
}
