package ab.todoapp.data.database

import ab.todoapp.common.Constant
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilderAndroid(ctx: Context): RoomDatabase.Builder<ToDoDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(Constant.DB_FILE_NAME)
    return Room.databaseBuilder<ToDoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}