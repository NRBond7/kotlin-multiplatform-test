
import Foundation
import UIKit
import main

class BaseViewController<T : ContractPresenter> : UIViewController, ContractView, Base {
    
    var presenter: T? = nil
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initPresenter()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        presenter!.attachView(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        presenter!.dropView()
    }
    
    func initPresenter() {
        // do nothing.  override in
    }
    
    func getSettingsFactory() -> Multiplatform_settingsSettingsFactory {
        return PlatformFunctionsKt.generateSettingsFactory()
    }
}

protocol Base {
    func initPresenter()
}
