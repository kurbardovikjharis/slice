package com.haris.data.entities

data class Group(
    val id: String,
    val name: String,
    val restaurants: List<Restaurant>
)