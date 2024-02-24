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
                url = "https://upload.wikimedia.org/wikipedia/commons/9/91/Pizza-3007395.jpg",
                rating = "New",
                time = "15-30 min",
                distance = "2.73 mi",
            ),
            RestaurantEntity(
                id = "3",
                name = "Piece of Chicago Carryout",
                url = "https://www.foodandwine.com/thmb/Wd4lBRZz3X_8qBr69UOu2m7I2iw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/classic-cheese-pizza-FT-RECIPE0422-31a2c938fc2546c9a07b7011658cfd05.jpg",
                rating = "4.2",
                time = "15-30 min",
                distance = "3.11 mi",
            ),
            RestaurantEntity(
                id = "4",
                name = "OH-IO PIZZA",
                url = "https://assets.icanet.se/e_sharpen:80,q_auto,dpr_1.25,w_718,h_718,c_lfill/imagevaultfiles/id_168121/cf_259/pizza_gjord_pa_tortillabrod.jpg",
                rating = "4.4",
                time = "15-30 min",
                distance = "3.16 mi",
            ),
        )
    }
}