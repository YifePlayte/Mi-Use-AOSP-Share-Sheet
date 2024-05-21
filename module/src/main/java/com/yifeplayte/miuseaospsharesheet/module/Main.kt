package com.yifeplayte.miuseaospsharesheet.module

import android.annotation.SuppressLint
import android.util.ArrayMap
import android.util.Log
import com.miui.base.MiuiStubRegistry

@Suppress("unused")
@SuppressLint("PrivateApi")
object Main {
    const val TAG = "MiUseAOSPShareSheet"

    @JvmStatic
    fun main() {
        runCatching {
            val sManifestStubsField =
                MiuiStubRegistry::class.java
                    .getDeclaredField("sManifestStubs")
                    .apply { isAccessible = true }
            val updateManifestMethod =
                MiuiStubRegistry::class.java
                    .getDeclaredMethod(
                        "updateManifest",
                        HashMap::class.java,
                        ArrayMap::class.java
                    ).apply { isAccessible = true }
            val sManifestStubs = sManifestStubsField.get(null)
            val newProviders = ArrayMap<String, MiuiStubRegistry.ImplProvider<*>>()
            newProviders["com.android.internal.app.ResolverActivityStub"] =
                ResolverActivityStubImplProxy.Provider()
            val updatedManifest = updateManifestMethod.invoke(null, sManifestStubs, newProviders)
            sManifestStubsField.set(null, updatedManifest)
            // Log.i(TAG, "Replace ResolverActivityStub provider success")
        }.onFailure {
            Log.e(TAG, "Replace ResolverActivityStub provider failed: $it", it)
        }
    }
}
