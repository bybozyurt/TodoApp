package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import common.Constant.DB_FILE_NAME

fun getDatabaseBuilderAndroid(ctx: Context): RoomDatabase.Builder<ToDoDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DB_FILE_NAME)
    return Room.databaseBuilder<ToDoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}