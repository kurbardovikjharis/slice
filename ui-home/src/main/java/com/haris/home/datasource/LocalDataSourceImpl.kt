package com.haris.home.datasource

import com.haris.home.data.RestaurantEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): List<RestaurantEntity> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call

        return@withContext listOf(
            RestaurantEntity(
                id = "1",
                name = "Buckeye Pizza Express",
                url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                rating = "4.8",
                time = "15-30 min",
                distance = "0.61 mi",
            ),
            RestaurantEntity(
                id = "2",
                name = "Dough Boyz Pizza",
                url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                rating = "New",
                time = "15-30 min",
                distance = "2.73 mi",
            ),
            RestaurantEntity(
                id = "3",
                name = "Piece of Chicago Carryout",
                url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                rating = "4.2",
                time = "15-30 min",
                distance = "3.11 mi",
            ),
            RestaurantEntity(
                id = "4",
                name = "OH-IO PIZZA",
                url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                rating = "4.4",
                time = "15-30 min",
                distance = "3.16 mi",
            ),
        )
    }
}