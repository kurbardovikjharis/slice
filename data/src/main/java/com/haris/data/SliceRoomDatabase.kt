package com.haris.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haris.data.entities.RestaurantEntity

@Database(
    entities = [
        RestaurantEntity::class,
    ],
    version = 1,
)

abstract class SliceRoomDatabase : RoomDatabase(), SliceDatabase
