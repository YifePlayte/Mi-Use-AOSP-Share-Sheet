package com.yifeplayte.miuseaospsharesheet.module

import android.annotation.SuppressLint
import android.util.Log
import com.android.internal.app.ResolverActivityStub
import com.miui.base.MiuiStubRegistry.registerSingleton

@Suppress("unused")
@SuppressLint("PrivateApi")
object Main {
    const val TAG = "MiUseAOSPShareSheet"

    @JvmStatic
    fun main() {
        runCatching {
            val resolverActivityStubImplProxy = ResolverActivityStubImplProxy()
            registerSingleton(ResolverActivityStub::class.java, resolverActivityStubImplProxy)
            ResolverActivityStub::class.java.declaredFields.firstOrNull {
                it.name == "sInstance"
            }?.apply {
                isAccessible = true
                set(null, resolverActivityStubImplProxy)
            }
            // Log.i(TAG, "Replace ResolverActivityStubImpl success")
        }.onFailure {
            Log.e(TAG, "Replace ResolverActivityStubImpl failed: $it", it)
        }
    }
}
