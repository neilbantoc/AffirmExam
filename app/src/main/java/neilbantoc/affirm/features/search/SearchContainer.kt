package neilbantoc.affirm.features.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import neilbantoc.affirm.base.BaseActivity

class SearchContainer() : BaseActivity(), SearchContract.Container {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state = ViewModelProviders.of(this).get(SearchState::class.java)
        val view = SearchView()
        val presenter = SearchPresenter(state, view, this)
        presenter.initPresenter(this)
        setContentView(view.getContentView())
    }
}