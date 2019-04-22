package neilbantoc.affirm.features.search

import neilbantoc.affirm.R
import neilbantoc.affirm.databinding.ActivitySearchBinding
import neilbantoc.framework.base.BaseView

class SearchView : SearchContract.View, BaseView<SearchState, SearchContract.View.ViewActions, ActivitySearchBinding>(actions= SearchContract.View.ViewActions(), resId = R.layout.activity_search)