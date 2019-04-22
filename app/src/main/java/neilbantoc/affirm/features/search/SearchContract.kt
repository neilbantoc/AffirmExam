package neilbantoc.affirm.features.search

import neilbantoc.framework.view.ObservableViewActions

class SearchContract {

    interface View : neilbantoc.framework.view.View<View.ViewActions> {

        interface EventHandler

        class ViewActions : ObservableViewActions
    }

    interface Container
}