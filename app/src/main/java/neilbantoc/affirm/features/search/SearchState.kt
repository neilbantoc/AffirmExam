package neilbantoc.affirm.features.search

import androidx.databinding.ObservableField
import neilbantoc.affirm.data.model.Photo
import neilbantoc.framework.model.ViewState

class SearchState : ViewState() {
    val photos = ObservableField<ArrayList<Photo>>()
    val page = ObservableField<Int>()
    val text = ObservableField<String>()
    val immersive = ObservableField<Boolean>()
}