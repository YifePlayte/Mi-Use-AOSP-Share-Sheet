# Mi Use AOSP Share Sheet

在小米系统下恢复AOSP的分享、打开方式界面。

### 构建

```shell
./gradlew assembleRelease
```

- 将`module/build/outputs/apk/release/module-release-unsigned.apk`解压
- 将解压后的`classes.dex`放在`magisk`目录下
- 将解压后的`lib`目录内的所有`.so`文件重命名为对应架构并放置在`magisk/zygisk`目录下
- 将`magisk`目录下的所有文件压缩为`zip`文件

最终模块安装包将类似以下结构：

```
module.zip
├── classes.dex
├── module.prop
├── zygisk
│   ├── arm64-v8a.so
│   ├── armeabi-v7a.so
│   ├── x86.so
│   └── x86_64.so
└── META-INF
    └── ...
```

### 第三方开源引用

##### MIT License

[kdrag0n/safetynet-fix](https://github.com/kdrag0n/safetynet-fix)

### License

[GNU General Public License v3.0](https://github.com/YifePlayte/Mi-Use-AOSP-Share-Sheet/blob/main/LICENSE)
