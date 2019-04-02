package donation

import base.Contract

interface DonationContract : Contract {

    interface View : Contract.View {

    }

    interface Presenter: Contract.Presenter<View> {

    }

}