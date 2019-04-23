package neilbantoc.affirm.features.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProviders
import neilbantoc.affirm.base.BaseActivity
import neilbantoc.framework.utils.AndroidBug5497Workaround


class SearchContainer() : BaseActivity(), SearchContract.Container {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state = ViewModelProviders.of(this).get(SearchState::class.java)
        val view = SearchView()
        val presenter = SearchPresenter(state, view, this, getApp().getFlickrApiService())
        presenter.initPresenter(this)
        setContentView(view.getContentView())
    }

    override fun onStart() {
        super.onStart()
        AndroidBug5497Workaround.getInstance(this).setListener()
    }

    override fun onStop() {
        super.onStop()
        AndroidBug5497Workaround.getInstance(this).removeListener()
    }

    override fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        hideKeyboard()
    }

    override fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    override fun hideKeyboard() {
        val view = findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}