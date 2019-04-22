package neilbantoc.affirm.features.search

import neilbantoc.framework.base.BasePresenter

class SearchPresenter(state: SearchState, view: SearchView, container: SearchContainer): SearchContract.View.EventHandler, BasePresenter<SearchState, SearchView, SearchContract.View.ViewActions, SearchContainer>(state, view, container) {
    override fun initDatabinding() {
        view.dataBinding.eventhandler = this
        view.dataBinding.state = state
    }

}