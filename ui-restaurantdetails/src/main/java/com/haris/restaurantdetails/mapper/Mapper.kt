package com.haris.restaurantdetails.mapper

import com.haris.data.RestaurantDetails
import com.haris.restaurantdetails.RestaurantDetailsEntity

internal fun RestaurantDetails.toRestaurantDetailsEntity(): RestaurantDetailsEntity =
    RestaurantDetailsEntity(
        id = id,
        name = name,
        url = url,
        rating = "$rating ($numberOfRatings)",
        time = time,
        distance = distance
    )