package com.timmy.workdemo2

import android.app.Application
import androidx.core.widget.TextViewCompat.AutoSizeTextType

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initAutoSize()
    }

    private fun initAutoSize() { //屏幕适配
//        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(false).setSupportSP(false)
//            .setSupportSubunits(Subunits.MM);
    }
}