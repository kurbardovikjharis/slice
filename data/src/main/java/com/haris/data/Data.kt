package com.haris.data

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.MenuItemEntity
import com.haris.data.entities.MenuSubItemEntity
import com.haris.data.entities.RestaurantEntity
import com.haris.data.entities.RestaurantDetailsEntity

val restaurantEntity1 = RestaurantEntity(
    id = "1",
    name = "Buckeye Pizza Express",
    url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
    rating = "4.8",
    numberOfRatings = "19",
    time = "15-30 min",
    distance = "0.61 mi",
)
val restaurantEntity2 = RestaurantEntity(
    id = "2",
    name = "Dough Boyz Pizza",
    url = "https://upload.wikimedia.org/wikipedia/commons/9/91/Pizza-3007395.jpg",
    rating = "New",
    numberOfRatings = "15",
    time = "15-30 min",
    distance = "2.73 mi",
)
val restaurantEntity3 = RestaurantEntity(
    id = "3",
    name = "Piece of Chicago Carryout",
    url = "https://www.foodandwine.com/thmb/Wd4lBRZz3X_8qBr69UOu2m7I2iw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/classic-cheese-pizza-FT-RECIPE0422-31a2c938fc2546c9a07b7011658cfd05.jpg",
    rating = "4.2",
    numberOfRatings = "41",
    time = "15-30 min",
    distance = "3.11 mi",
)
val restaurantEntity4 = RestaurantEntity(
    id = "4",
    name = "OH-IO PIZZA",
    url = "https://assets.icanet.se/e_sharpen:80,q_auto,dpr_1.25,w_718,h_718,c_lfill/imagevaultfiles/id_168121/cf_259/pizza_gjord_pa_tortillabrod.jpg",
    rating = "4.4",
    numberOfRatings = "14",
    time = "15-30 min",
    distance = "3.16 mi",
)
val restaurantEntity5 = RestaurantEntity(
    id = "5",
    name = "Borgata Pizza Cafe Budd Dairy",
    url = "https://offloadmedia.feverup.com/secretstockholm.co/wp-content/uploads/2023/08/25171232/1889-1-1024x728.jpg",
    rating = "4.8",
    numberOfRatings = "66",
    time = "15-30 min",
    distance = "4.26 mi",
)
val restaurantEntity6 = RestaurantEntity(
    id = "6",
    name = "Jet's Pizza",
    url = "https://preppykitchen.com/wp-content/uploads/2021/10/Cheese-Pizza-Recipe-Card-500x500.jpg",
    rating = "4.3",
    numberOfRatings = "22",
    time = "15-30 min",
    distance = "4.33 mi",
)
val restaurantEntity7 = RestaurantEntity(
    id = "7",
    name = "Brio Italian Grille",
    url = "https://s23209.pcdn.co/wp-content/uploads/2022/05/Sheet-Pan-Pizza211129_DAMN-DELICIOUS_Sheet-Pan-Pizza_482.jpg",
    rating = "New",
    numberOfRatings = "0",
    time = "30-45 min",
    distance = "4.35 mi",
)
val restaurantEntity8 = RestaurantEntity(
    id = "8",
    name = "Del Baggio Pizzeria",
    url = "https://static.mathem.se/shared/images/recipes/doublelarge/napolitansk-pizza-mozzarella_foto_andrea-klintbjer_mathem.jpeg",
    rating = "4.9",
    numberOfRatings = "41",
    time = "15-30 min",
    distance = "4.41 mi",
)
val restaurantEntity9 = RestaurantEntity(
    id = "9",
    name = "Buca Di Beppo",
    url = "https://www.santamariaworld.com/optimized/recipe-large/globalassets/_recipes/tex-mex/pizza_gronsaker.jpg",
    rating = "New",
    numberOfRatings = "0",
    time = "15-30 min",
    distance = "4.70 mi",
)
val restaurantEntity10 = RestaurantEntity(
    id = "10",
    name = "Piece of Chicago Carryout",
    url = "https://www.seriouseats.com/thmb/1KXAouO18DZnB7JfoaEUhP5z81c=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__2017__02__20170216-detroit-style-pizza-47-1500x1125-1-233d75e6021048b3bf3cf28bd59d310b.jpg",
    rating = "4.2",
    numberOfRatings = "18",
    time = "15-30 min",
    distance = "3.11 mi",
)

val restaurants = listOf(
    restaurantEntity1,
    restaurantEntity2,
    restaurantEntity3,
    restaurantEntity4,
    restaurantEntity5,
    restaurantEntity6,
    restaurantEntity7,
    restaurantEntity8,
    restaurantEntity9,
    restaurantEntity10
)

val groupEntity1 = GroupEntity(
    id = "1",
    name = "Gluten-free / vegan",
    restaurantEntities = restaurants
)
val groupEntity2 = GroupEntity(
    id = "2",
    name = "Hot & fresh to slice",
    restaurantEntities = restaurants.sortedBy { it.numberOfRatings }
)
val groupEntity3 = GroupEntity(
    id = "3",
    name = "Deepest discounts",
    restaurantEntities = restaurants.sortedBy { it.rating }
)
val groupEntity4 = GroupEntity(
    id = "4",
    name = "Pizza near you",
    restaurantEntities = restaurants.sortedBy { it.name }
)

val groups = listOf(groupEntity1, groupEntity2, groupEntity3, groupEntity4)

val menuSubItemEntity1 = MenuSubItemEntity(
    id = "1",
    title = "Cheese Pizza",
    description = "Classic cheese or create your own pizza",
    price = "$10.99"
)
val menuSubItemEntity2 = MenuSubItemEntity(
    id = "2",
    title = "Buckeye Thin Crust Pizza",
    description = null,
    price = "$14.99"
)
val menuSubItemEntity3 = MenuSubItemEntity(
    id = "3",
    title = "Buckeye Thick Crust Pizza",
    description = null,
    price = "$16.99"
)
val menuSubItemEntity4 = MenuSubItemEntity(
    id = "4",
    title = "Buckeye Pizza",
    description = "Pepperoni, sausage, ham, green peppers, onions, mushrooms, extra cheese",
    price = "$13.99"
)
val menuSubItemEntity5 = MenuSubItemEntity(
    id = "5",
    title = "Shrimp Scampi Pizza",
    description = "Mozzarella, cheese, garlic sauce, shrimp & parmesan cheese",
    price = "$13.99"
)
val menuSubItemEntity6 = MenuSubItemEntity(
    id = "6",
    title = "Meat Lovers Pizza",
    description = "Pepperoni, sausage, ham, ground beef, bacon & extra cheese",
    price = "$16.99"
)
val menuSubItemEntity7 = MenuSubItemEntity(
    id = "7",
    title = "Veggie Pizza",
    description = "Choose up to 5 vegetables & extra cheese",
    price = "$16.99"
)
val menuSubItemEntity8 = MenuSubItemEntity(
    id = "8",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItemEntity9 = MenuSubItemEntity(
    id = "9",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItemEntity10 = MenuSubItemEntity(
    id = "10",
    title = "Buckeye Pizza",
    description = "Pepperoni, sausage, ham, green peppers, onions, mushrooms, extra cheese",
    price = "$13.99"
)
val menuSubItemEntity11 = MenuSubItemEntity(
    id = "11",
    title = "Shrimp Scampi Pizza",
    description = "Mozzarella, cheese, garlic sauce, shrimp & parmesan cheese",
    price = "$13.99"
)
val menuSubItemEntity12 = MenuSubItemEntity(
    id = "12",
    title = "Meat Lovers Pizza",
    description = "Pepperoni, sausage, ham, ground beef, bacon & extra cheese",
    price = "$16.99"
)
val menuSubItemEntity13 = MenuSubItemEntity(
    id = "13",
    title = "Veggie Pizza",
    description = "Choose up to 5 vegetables & extra cheese",
    price = "$16.99"
)
val menuSubItemEntity14 = MenuSubItemEntity(
    id = "14",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItemEntity15 = MenuSubItemEntity(
    id = "15",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$13.99"
)

val menuSubItems = listOf(
    menuSubItemEntity1,
    menuSubItemEntity2,
    menuSubItemEntity3,
    menuSubItemEntity4,
    menuSubItemEntity5,
    menuSubItemEntity6,
    menuSubItemEntity7,
    menuSubItemEntity8,
    menuSubItemEntity9,
    menuSubItemEntity10,
    menuSubItemEntity11,
    menuSubItemEntity12,
    menuSubItemEntity13,
    menuSubItemEntity14,
    menuSubItemEntity15
)

val restaurantDetailsEntity1 = RestaurantDetailsEntity(
    id = "1",
    name = "Buckeye Pizza Express",
    url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
    rating = "4.8",
    numberOfRatings = "19",
    time = "15-30 min",
    distance = "0.61 mi",
    menuItemEntities = listOf(
        MenuItemEntity(
            id = "1",
            title = "Pizza",
            items = listOf(menuSubItemEntity1, menuSubItemEntity2, menuSubItemEntity3)
        ),
        MenuItemEntity(
            id = "2",
            title = "Buckeye Specialty Pizzas",
            items = listOf(
                menuSubItemEntity4,
                menuSubItemEntity5,
                menuSubItemEntity6,
                menuSubItemEntity7,
                menuSubItemEntity8,
                menuSubItemEntity9,
                menuSubItemEntity10,
                menuSubItemEntity11,
                menuSubItemEntity12,
                menuSubItemEntity13,
                menuSubItemEntity14,
                menuSubItemEntity15,
            )
        )
    )
)

val restaurantsDetails = listOf(restaurantDetailsEntity1)