package database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getDatabaseBuilderAndroid(ctx: Context): ToDoDatabase {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(dbFileName)
    return Room.databaseBuilder<ToDoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver()).fallbackToDestructiveMigration(true).build()
}