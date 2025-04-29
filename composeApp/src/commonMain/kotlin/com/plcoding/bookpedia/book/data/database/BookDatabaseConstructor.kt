
package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

/*No need to add actual for this as room DB internally creates it*/
@Suppress("KotlinNoActualForExpect", "NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}