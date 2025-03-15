package ab.todoapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilderAndroid(ctx: Context): RoomDatabase.Builder<ToDoDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("todo.db")
    return Room.databaseBuilder<ToDoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}