package base

abstract class BasePresenter<V : Contract.View> : Contract.Presenter<Contract.View> {

    lateinit var view: V

    // onStart, viewWillAppear
    override fun attachView(view: Contract.View) {
        this.view = view as V
        onViewTaken()
    }

    private fun onViewTaken() {
        view.logScreenLoad(screenName())
    }

    // onStop, viewWillDisappear
    override fun dropView() {
        onViewDropped()
    }

    private fun onViewDropped() {}

    abstract fun screenName(): String

}
