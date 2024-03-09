package com.haris.data.entities

data class RestaurantDetailsEntity(
    val id: String,
    val name: String,
    val url: String,
    val rating: String,
    val numberOfRatings: String,
    val time: String,
    val distance: String,
    val menuItemEntities: List<MenuItemEntity>
)
