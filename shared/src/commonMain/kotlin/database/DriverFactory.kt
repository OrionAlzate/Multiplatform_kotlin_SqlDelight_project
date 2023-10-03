package database

import app.cash.sqldelight.db.SqlDriver
import com.mantum.database.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

