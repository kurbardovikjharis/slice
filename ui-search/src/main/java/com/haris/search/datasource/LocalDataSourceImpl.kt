package com.haris.search.datasource

import com.haris.search.data.Group
import com.haris.search.data.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    private val restaurants = listOf(
        Restaurant(
            id = "1",
            name = "Buckeye Pizza Express",
            url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
            rating = "4.8",
            numberOfRatings = "19",
            time = "15-30 min",
            distance = "0.61 mi",
        ),
        Restaurant(
            id = "2",
            name = "Buckeye Pizza Express",
            url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
            rating = "4.8",
            numberOfRatings = "19",
            time = "15-30 min",
            distance = "0.61 mi",
        ),
        Restaurant(
            id = "3",
            name = "Buckeye Pizza Express",
            url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
            rating = "4.8",
            numberOfRatings = "19",
            time = "15-30 min",
            distance = "0.61 mi",
        ),
    )

    override suspend fun getData(): List<Group> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call

        return@withContext listOf(
            Group(
                id = "1",
                name = "Gluten-free / vegan",
                restaurants = listOf(
                    Restaurant(
                        id = "1",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "2",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "3",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                ),
            ),
            Group(
                id = "2",
                name = "Hot & fresh to slice",
                restaurants = listOf(
                    Restaurant(
                        id = "1",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "2",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "3",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                ),
            ),
            Group(
                id = "3",
                name = "Hot & fresh to slice",
                restaurants = listOf(
                    Restaurant(
                        id = "1",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "2",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "3",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                ),
            ),
            Group(
                id = "4",
                name = "Hot & fresh to slice",
                restaurants = listOf(
                    Restaurant(
                        id = "1",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "2",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                    Restaurant(
                        id = "3",
                        name = "Buckeye Pizza Express",
                        url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
                        rating = "4.8",
                        numberOfRatings = "19",
                        time = "15-30 min",
                        distance = "0.61 mi",
                    ),
                ),
            ),
        )
    }

    override suspend fun searchRestaurants(term: String): List<Restaurant> {
        return restaurants.filter { it.name.contains(term) }
    }
}