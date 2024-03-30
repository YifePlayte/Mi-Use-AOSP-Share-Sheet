package com.yifeplayte.miuseaospsharesheet.module

import android.annotation.SuppressLint
import android.app.ActivityThread.currentApplication
import android.provider.Settings.System.getInt
import android.util.Log
import com.android.internal.app.ResolverActivityStubImpl
import com.yifeplayte.miuseaospsharesheet.module.Main.TAG

class ResolverActivityStubImplProxy : ResolverActivityStubImpl() {

    @SuppressLint("PrivateApi")
    override fun useAospShareSheet(): Boolean {
        runCatching {
            val currentApplication = currentApplication()
            if (currentApplication == null) {
                Log.i(TAG, "currentApplication null!")
                return super.useAospShareSheet()
            }
            if (currentApplication.packageName == "android") return super.useAospShareSheet()
            return getInt(currentApplication.contentResolver, "mishare_enabled") != 1
        }.onFailure {
            Log.e(TAG, "useAospShareSheet failed!", it)
        }
        return super.useAospShareSheet()
    }
}
