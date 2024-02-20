package com.yifeplayte.miuseaospsharesheet.module

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import android.util.Log
import com.android.internal.app.ResolverActivityStubImpl
import com.yifeplayte.miuseaospsharesheet.module.Main.TAG


class ResolverActivityStubImplProxy : ResolverActivityStubImpl() {

    @SuppressLint("PrivateApi")
    override fun useAospShareSheet(): Boolean {
        runCatching {
            val clazzActivityThread = Class.forName("android.app.ActivityThread")
            val mApplication =
                clazzActivityThread.getMethod("currentApplication").invoke(null) as Application?
            if (mApplication == null) {
                Log.i(TAG, "currentApplication null!")
                return true
            }
            return Settings.System.getInt(mApplication.contentResolver, "mishare_enabled") != 1
        }.onFailure {
            Log.e(TAG, "useAospShareSheet failed!", it)
        }
        return true
    }
}
