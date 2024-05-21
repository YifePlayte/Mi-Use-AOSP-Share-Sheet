package com.yifeplayte.miuseaospsharesheet.module;

import static com.yifeplayte.miuseaospsharesheet.module.Main.TAG;

import android.app.ActivityThread;
import android.app.Application;
import android.provider.Settings;
import android.util.Log;

import com.android.internal.app.ResolverActivityStubImpl;
import com.miui.base.MiuiStubRegistry;

import org.lsposed.hiddenapibypass.HiddenApiBypass;

public class ResolverActivityStubImplProxy extends ResolverActivityStubImpl {
    @Override
    public boolean useAospShareSheet() {
        try {
            Application currentApplication = ActivityThread.currentApplication();
            if (currentApplication == null) {
                Log.i(TAG, "currentApplication null!");
                return super.useAospShareSheet();
            }
            return Settings.System.getInt(currentApplication.getContentResolver(), "mishare_enabled") != 1;
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "useAospShareSheet failed!", e);
        }
        return super.useAospShareSheet();
    }

    public static class Provider implements MiuiStubRegistry.ImplProvider<ResolverActivityStubImpl> {
        private final MiuiStubRegistry.ImplProvider<ResolverActivityStubImpl> originalProvider = new ResolverActivityStubImpl.Provider();

        @Override
        public ResolverActivityStubImpl provideNewInstance() {
            return originalProvider.provideNewInstance();
        }

        @Override
        public ResolverActivityStubImpl provideSingleton() {
            HiddenApiBypass.addHiddenApiExemptions("Lcom/android/internal/app/ResolverActivityStubImpl;");
            return Singleton.singleton;
        }

        public static class Singleton {
            private static final ResolverActivityStubImpl singleton = new ResolverActivityStubImplProxy();
        }
    }
}
