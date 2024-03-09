package com.haris.data.entities

data class MenuItemEntity(
    val id: String,
    val title: String,
    val items: List<MenuSubItemEntity>
)