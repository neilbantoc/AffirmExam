package neilbantoc.framework.base


import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import neilbantoc.framework.container.LifecycleContainer
import neilbantoc.framework.container.LifecycleEvent
import neilbantoc.framework.container.LifecycleEvents
import java.lang.ref.WeakReference


open class BaseActivityContainer(): AppCompatActivity(), LifecycleContainer {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private val activityLifecycleObservable = PublishSubject.create<LifecycleEvent>()
    private val callbackToObservableDelegate = CallbackToObservableDelegate(activityLifecycleObservable)

    override fun onCreate(savedInstanceState: Bundle?) {
        application.registerActivityLifecycleCallbacks(callbackToObservableDelegate)
        super.onCreate(savedInstanceState)
    }

    override fun observeLifecycle(): Observable<LifecycleEvent> {
        return activityLifecycleObservable
    }

    override fun onDestroy() {
        application.unregisterActivityLifecycleCallbacks(callbackToObservableDelegate)
        super.onDestroy()
    }

    class CallbackToObservableDelegate(val observable: PublishSubject<LifecycleEvent>) : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_PAUSE))
        }

        override fun onActivityResumed(activity: Activity?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_RESUME))
        }

        override fun onActivityStarted(activity: Activity?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_RESUME))
        }

        override fun onActivityDestroyed(activity: Activity?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_DESTROY))
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }

        override fun onActivityStopped(activity: Activity?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_STOP))
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            observable.onNext(LifecycleEvent(WeakReference(activity), LifecycleEvents.ON_CREATE))
        }

    }
}