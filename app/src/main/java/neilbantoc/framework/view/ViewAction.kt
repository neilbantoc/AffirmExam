package neilbantoc.framework.view

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ViewAction<T> {
    private val observable = PublishSubject.create<T>()

    fun observable() : Observable<T> {
        return observable
    }

    fun publish(data: T) {
        observable.onNext(data)
    }
}