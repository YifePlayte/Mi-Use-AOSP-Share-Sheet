package com.miui.base;

public class MiuiStubRegistry {
    public static <P, Q extends P> void registerSingleton(Class<P> baseClazz, Q impl) {
        throw new IllegalArgumentException("Stub!");
    }
}
