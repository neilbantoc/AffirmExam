package neilbantoc.affirm.features.search

import neilbantoc.framework.view.ObservableViewActions
import neilbantoc.framework.view.ViewAction

class SearchContract {

    interface View : neilbantoc.framework.view.View<View.ViewActions> {

        fun clearPhotos()

        interface EventHandler {
            fun onSearchClick()
        }

        class ViewActions : ObservableViewActions {
            val scroll = ViewAction<Int>()
            val itemsTillEnd = ViewAction<Int>()
        }
    }

    interface Container {
        fun hideSystemUI()
        fun showSystemUI()
        fun hideKeyboard()
    }
}