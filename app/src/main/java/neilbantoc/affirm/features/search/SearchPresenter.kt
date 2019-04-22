package neilbantoc.affirm.features.search

import neilbantoc.affirm.api.FlickrApiService
import neilbantoc.framework.base.BasePresenter

class SearchPresenter(state: SearchState, view: SearchView, container: SearchContainer, val flickrApiService: FlickrApiService): SearchContract.View.EventHandler, BasePresenter<SearchState, SearchView, SearchContract.View.ViewActions, SearchContainer>(state, view, container) {
    override fun initDatabinding() {
        view.dataBinding.eventhandler = this
        view.dataBinding.state = state
    }

    override fun onViewInitialized() {
        super.onViewInitialized()
        addDisposable(flickrApiService.search("dogs", 1).subscribe({ response ->
            response.photos?.apply {

            }
        }, { error ->
            error.printStackTrace()
        }))
    }

}