package com.acme.core.logging

import co.touchlab.kermit.Logger as KermitLogger

interface Logger {
    fun v(tag: String, message: String)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun w(tag: String, message: String, throwable: Throwable? = null)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}

class KermitLoggerFacade : Logger {
    private val logger = KermitLogger()

    override fun v(tag: String, message: String) { logger.v(tag) { message } }
    override fun d(tag: String, message: String) { logger.d(tag) { message } }
    override fun i(tag: String, message: String) { logger.i(tag) { message } }
    override fun w(tag: String, message: String, throwable: Throwable?) { logger.w(tag, throwable) { message } }
    override fun e(tag: String, message: String, throwable: Throwable?) { logger.e(tag, throwable) { message } }
}

object Logs {
    var logger: Logger = KermitLoggerFacade()
}