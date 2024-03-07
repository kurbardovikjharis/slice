package com.haris.data.entities

import com.haris.data.entities.MenuItem

data class RestaurantDetails(
    val id: String,
    val name: String,
    val url: String,
    val rating: String,
    val numberOfRatings: String,
    val time: String,
    val distance: String,
    val menuItems: List<MenuItem>
)
