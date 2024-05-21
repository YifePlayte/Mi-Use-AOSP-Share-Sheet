package com.android.internal.app;

import com.miui.base.MiuiStubRegistry;

public class ResolverActivityStubImpl extends ResolverActivityStub {
    @Override
    public boolean useAospShareSheet() {
        throw new IllegalArgumentException("Stub!");
    }

    public static final class Provider implements MiuiStubRegistry.ImplProvider<ResolverActivityStubImpl> {
        @Override
        public ResolverActivityStubImpl provideNewInstance() {
            throw new IllegalArgumentException("Stub!");
        }

        @Override
        public ResolverActivityStubImpl provideSingleton() {
            throw new IllegalArgumentException("Stub!");
        }
    }
}
