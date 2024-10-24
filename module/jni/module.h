#include <android/log.h>
#pragma once

namespace mi_use_aosp_share_sheet {

    static constexpr auto TAG = "MiUseAOSPShareSheet/JNI";
    static constexpr auto MODULE_DEX_PATH = "/data/adb/modules/mi_use_aosp_share_sheet/classes.dex";
    static constexpr auto ENTRY_CLASS_NAME = "com.yifeplayte.miuseaospsharesheet.module.Main";

#define LOGD(...)     __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGI(...)     __android_log_print(ANDROID_LOG_INFO,  TAG, __VA_ARGS__)
#define LOGE(...)     __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGERRNO(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__ ": %d (%s)", errno, strerror(errno))

}
