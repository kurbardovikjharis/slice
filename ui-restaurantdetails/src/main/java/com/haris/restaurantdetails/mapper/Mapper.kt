package com.haris.restaurantdetails.mapper

import com.haris.data.entities.RestaurantDetailsEntity
import com.haris.restaurantdetails.RestaurantDetails

internal fun RestaurantDetailsEntity.toRestaurantDetailsEntity(): RestaurantDetails =
    RestaurantDetails(
        id = id,
        name = name,
        url = url,
        rating = rating,
        numberOfRatings = numberOfRatings,
        time = time,
        distance = distance,
        menuItems = menuItemEntities
    )