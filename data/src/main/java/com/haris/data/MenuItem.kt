package com.haris.data

data class MenuItem(
    val id: String,
    val title: String,
    val items: List<MenuSubItem>
)