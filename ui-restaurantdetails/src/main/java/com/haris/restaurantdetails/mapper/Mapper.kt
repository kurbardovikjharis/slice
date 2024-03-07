package com.haris.restaurantdetails.mapper

import com.haris.data.entities.MenuItem
import com.haris.data.entities.MenuSubItem
import com.haris.data.entities.RestaurantDetails
import com.haris.restaurantdetails.RestaurantDetailsEntity
import com.haris.restaurantdetails.data.MenuItemEntity
import com.haris.restaurantdetails.data.MenuSubItemEntity

internal fun RestaurantDetails.toRestaurantDetailsEntity(): RestaurantDetailsEntity =
    RestaurantDetailsEntity(
        id = id,
        name = name,
        url = url,
        rating = rating,
        numberOfRatings = numberOfRatings,
        time = time,
        distance = distance,
        menuItems = menuItems.map { it.toMenuItemEntity() }
    )

internal fun MenuItem.toMenuItemEntity(): MenuItemEntity =
    MenuItemEntity(
        id = id,
        title = title,
        items = items.map { it.toMenuSubItemEntity() }
    )

internal fun MenuSubItem.toMenuSubItemEntity(): MenuSubItemEntity =
    MenuSubItemEntity(
        id = id,
        title = title,
        description = description ?: "",
        price = price
    )
