package neilbantoc.framework.view

import android.content.Context
import android.view.View

interface View <A : ObservableViewActions> {
    fun getContentView(): View
    fun initView(context: Context)
    fun actions(): A
}