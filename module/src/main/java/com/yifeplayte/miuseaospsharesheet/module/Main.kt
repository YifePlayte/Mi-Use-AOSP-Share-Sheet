package com.yifeplayte.miuseaospsharesheet.module

import android.annotation.SuppressLint
import android.util.Log
import com.android.internal.app.ResolverActivityStub

@Suppress("unused")
@SuppressLint("PrivateApi")
object Main {
    const val TAG = "MiUseAOSPShareSheet"

    @JvmStatic
    fun main() {
        runCatching {
            val clazzMiuiStubRegistry = Class.forName("com.miui.base.MiuiStubRegistry")
            val methodRegisterSingleton =
                clazzMiuiStubRegistry.declaredMethods.first { it.name == "registerSingleton" }
            val resolverActivityStubImplProxy = ResolverActivityStubImplProxy()
            methodRegisterSingleton.invoke(
                null, ResolverActivityStub::class.java, resolverActivityStubImplProxy
            )
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
