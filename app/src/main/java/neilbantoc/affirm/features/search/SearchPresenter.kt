package neilbantoc.affirm.features.search

import io.reactivex.android.schedulers.AndroidSchedulers
import neilbantoc.affirm.api.FlickrApiService
import neilbantoc.affirm.data.model.Photo
import neilbantoc.framework.base.BasePresenter
import java.util.concurrent.TimeUnit

class SearchPresenter(state: SearchState, view: SearchView, container: SearchContainer, val flickrApiService: FlickrApiService): SearchContract.View.EventHandler, BasePresenter<SearchState, SearchView, SearchContract.View.ViewActions, SearchContainer>(state, view, container) {

    private var scrollingDown = false
    private var fetching = false

    override fun initDatabinding() {
        view.dataBinding.eventhandler = this
        view.dataBinding.state = state
    }

    override fun onViewInitialized() {
        super.onViewInitialized()
        state.page.set(0)
        state.text.set("dogs")
        fetchItems()
        addDisposable(view.actions.scroll.observable()
            .sample(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe() { scrollBy ->
                if (scrollBy > 0 && !scrollingDown) {
                    container.hideSystemUI()
                    scrollingDown = true
                } else if (scrollBy < 0 && scrollingDown) {
                    container.showSystemUI()
                    scrollingDown = false
                }
        })

        addDisposable(view.actions.itemsTillEnd.observable()
            .subscribe({ itemsLeft ->
                if (itemsLeft < 15 && !fetching) {
                    fetchItems()
                }
            }, { error ->
                error.printStackTrace()
            }))
    }

    private fun fetchItems() {
        state.text.get()?.apply {
            fetching = true
            addDisposable(flickrApiService.search(this, state.page.get()!!).subscribe({ response ->
                response.photos?.apply {
                    val items = ArrayList<Photo>()
                    state.photos.get()?.apply {
                        items.addAll(this)
                    }
                    items.addAll(this.photos)
                    state.photos.set(items)
                    state.page.set(state.page.get()!! + 1)
                    fetching = false
                }
            }, { error ->
                error.printStackTrace()
                fetching = false
            }))
        }
    }

}