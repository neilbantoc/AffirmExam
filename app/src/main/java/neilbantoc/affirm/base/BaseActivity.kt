package neilbantoc.affirm.base

import neilbantoc.affirm.App
import neilbantoc.framework.base.BaseActivityContainer

open class BaseActivity() : BaseActivityContainer() {
    fun getApp(): App {
        return application as App
    }
}