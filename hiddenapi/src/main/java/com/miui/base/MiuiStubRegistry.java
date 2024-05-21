package com.miui.base;

import android.util.ArrayMap;

import java.util.HashMap;

public class MiuiStubRegistry {
    private static volatile HashMap<String, ImplProvider<?>> sManifestStubs = new HashMap<>();

    public static <P, Q extends P> void registerSingleton(Class<P> baseClazz, Q impl) {
        throw new IllegalArgumentException("Stub!");
    }

    private static HashMap<String, ImplProvider<?>> updateManifest(HashMap<String, ImplProvider<?>> manifestStubs, ArrayMap<String, ImplProvider<?>> newProviders) {
        throw new IllegalArgumentException("Stub!");
    }

    public interface ImplProvider<T> {
        T provideNewInstance();

        T provideSingleton();
    }
}
