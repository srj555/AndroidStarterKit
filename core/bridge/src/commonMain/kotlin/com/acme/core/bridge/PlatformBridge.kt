package com.acme.core.bridge

expect object PlatformBridge {
    val isNetworkConnected: Boolean
    val filesDirPath: String
}