package base

interface Contract {
    interface View {
        // fun displayReadyState()
        // fun displayErrorState()
        // fun displayLoadingState()
    }

    interface Presenter<V : Contract.View> {

        fun attachView(view: V)

        fun dropView()
    }
}

