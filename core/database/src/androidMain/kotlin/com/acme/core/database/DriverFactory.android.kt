package com.acme.core.database

import app.cash.sqldelight.android.AndroidSqliteDriver
import app.cash.sqldelight.db.SqlDriver
import android.content.Context

class DriverFactory(private val context: Context) {
    fun createDriver(): SqlDriver = AndroidSqliteDriver(AppDatabase.Schema, context, "app.db")
}