import UIKit
import main

class SettingsViewController: BaseViewController<SettingsContractPresenter>, SettingsContractView {
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func initPresenter() {
        presenter = SettingsPresenter()
    }
    
    func populateSettings(unit: String, barbellTitle: String, barbellWeight: String, plateWeightTitle: String, plateWeight: String, conroyModeEnabled: Bool) {
        
    }
    
    func setConroyMode(conroyModeEnabled: Bool) {
        
    }
    
    func showPickerDialog(title: String, options: [String], pickerSetting: SettingsContractPickerSetting) {
        
    }
}
