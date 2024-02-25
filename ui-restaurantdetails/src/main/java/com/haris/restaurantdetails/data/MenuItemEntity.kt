package com.haris.restaurantdetails.data

data class MenuItemEntity(
    val id: String,
    val title: String,
    val items: List<MenuSubItemEntity>
)