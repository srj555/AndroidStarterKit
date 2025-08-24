package com.acme.core.bridge

actual object PlatformBridge {
    actual val isNetworkConnected: Boolean get() = true
    actual val filesDirPath: String get() = "/data/data/placeholder/files"
}