
import Foundation
import UIKit
import main
import Firebase

class BaseViewController<T : ContractPresenter> : UIViewController, ContractView, Base {
    
    var presenter: T? = nil
    
    var screenName: String? = nil
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initPresenter()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter!.attachView(view: self)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        presenter!.dropView()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        Analytics.setScreenName(screenName, screenClass: String(describing: self))
    }
    
    func initPresenter() {
        // do nothing.  override in
    }
    
    func getSettingsFactory() -> Multiplatform_settingsSettingsFactory {
        return PlatformFunctionsKt.generateSettingsFactory()
    }
    
    func logScreenLoad(screenName: String) {
        self.screenName = screenName
    }
    
    func logEvent(eventName: String, params: [String : String]) {
        Analytics.logEvent(eventName, parameters: params)
    }
}

protocol Base {
    func initPresenter()
}
