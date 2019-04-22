package neilbantoc.framework.base

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import neilbantoc.framework.model.ViewState
import neilbantoc.framework.view.ObservableViewActions
import neilbantoc.framework.view.View

abstract class BaseView<S : ViewState, A : ObservableViewActions, D : ViewDataBinding>(val resId: Int, val actions: A) : View<A> {

    lateinit var layout: android.view.View
    lateinit var dataBinding: D

    override fun initView(context: Context) {
        val inflater = LayoutInflater.from(context)
        layout = inflater.inflate(resId, null)
        dataBinding = DataBindingUtil.bind<D>(layout)!!
        onViewInitialized()
    }

    open fun onViewInitialized() { }

    override fun getContentView(): android.view.View {
        return layout
    }

    override fun actions(): A {
        return actions
    }

    fun getContext(): Context {
        return getContentView().context
    }

    fun getString(resId: Int): String {
        return getContext().getString(resId)
    }
}