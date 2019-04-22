package neilbantoc.affirm.features.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import neilbantoc.affirm.base.BaseActivity

class SearchContainer() : BaseActivity(), SearchContract.Container {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state = ViewModelProviders.of(this).get(SearchState::class.java)
        val view = SearchView()
        val presenter = SearchPresenter(state, view, this, getApp().getFlickrApiService())
        presenter.initPresenter(this)
        setContentView(view.getContentView())
    }

    override fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}