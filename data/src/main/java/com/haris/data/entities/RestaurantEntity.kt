package com.haris.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestaurantEntity(
    @PrimaryKey val id: String,
    val name: String,
    val url: String,
    val rating: String,
    @ColumnInfo(name = "number_of_ratings") val numberOfRatings: String,
    val time: String,
    val distance: String,
)