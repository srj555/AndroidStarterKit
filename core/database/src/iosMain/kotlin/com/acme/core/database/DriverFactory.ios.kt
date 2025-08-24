package com.acme.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class DriverFactory {
    fun createDriver(): SqlDriver = NativeSqliteDriver(AppDatabase.Schema, "app.db")
}