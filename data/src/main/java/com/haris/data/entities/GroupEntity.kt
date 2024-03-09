package com.haris.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    @PrimaryKey val id: String,
    val name: String,
    val restaurantEntities: List<RestaurantEntity>
)