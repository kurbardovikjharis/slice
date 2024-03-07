package com.haris.data.entities

data class MenuItem(
    val id: String,
    val title: String,
    val items: List<MenuSubItem>
)