package com.plcoding.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val appContext = context.applicationContext
        return Room.databaseBuilder(
            context = appContext,
            klass = FavoriteBookDatabase::class.java,
            name = FavoriteBookDatabase.DB_NAME
        )
    }
}