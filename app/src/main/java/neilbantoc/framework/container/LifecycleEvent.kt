package neilbantoc.framework.container

import java.lang.ref.WeakReference

data class LifecycleEvent(val target : WeakReference<Any?>, val event : LifecycleEvents)