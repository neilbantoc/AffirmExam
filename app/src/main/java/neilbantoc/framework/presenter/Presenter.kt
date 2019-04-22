package neilbantoc.framework.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import neilbantoc.framework.container.LifecycleEvent

interface Presenter: Consumer<LifecycleEvent> {
    fun getDisposables(): CompositeDisposable
    fun addDisposable(disposable: Disposable)
}