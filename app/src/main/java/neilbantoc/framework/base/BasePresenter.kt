package neilbantoc.framework.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import neilbantoc.framework.container.LifecycleContainer
import neilbantoc.framework.container.LifecycleEvent
import neilbantoc.framework.container.LifecycleEvents
import neilbantoc.framework.model.ViewState
import neilbantoc.framework.presenter.Presenter
import neilbantoc.framework.view.ObservableViewActions
import neilbantoc.framework.view.View

abstract class BasePresenter<S: ViewState, V : View<A>, A: ObservableViewActions, C: LifecycleContainer> (val state: S, val view: V, val container: C): Presenter {

    private val compositeDisposable = CompositeDisposable()

    fun initPresenter(context: Context) {
        val lifecycleObservable = container.observeLifecycle()
        addDisposable(lifecycleObservable.subscribe(this))

        view.initView(context)

        initDatabinding()
        onViewInitialized()
    }

    /**
     * Subclasses must init databinding components by performing these two lines of code:
     *
     * view.dataBinding.eventhandler = this
     * view.dataBinding.viewstate = state
     */
    abstract fun initDatabinding()

    open fun onViewInitialized() {}

    override fun getDisposables(): CompositeDisposable {
        return compositeDisposable
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun accept(event: LifecycleEvent?) {
        if (!container.equals(event?.target?.get())) {
            return
        }

        if (LifecycleEvents.ON_DESTROY.equals(event)) {
            compositeDisposable.clear()
        }

        onLifecycleEvent(event?.event!!)
    }

    open fun onLifecycleEvent(event: LifecycleEvents) {}

}