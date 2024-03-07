package com.haris.data

import com.haris.data.entities.Group
import com.haris.data.entities.MenuItem
import com.haris.data.entities.MenuSubItem
import com.haris.data.entities.Restaurant
import com.haris.data.entities.RestaurantDetails

val restaurant1 = Restaurant(
    id = "1",
    name = "Buckeye Pizza Express",
    url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
    rating = "4.8",
    numberOfRatings = "19",
    time = "15-30 min",
    distance = "0.61 mi",
)
val restaurant2 = Restaurant(
    id = "2",
    name = "Dough Boyz Pizza",
    url = "https://upload.wikimedia.org/wikipedia/commons/9/91/Pizza-3007395.jpg",
    rating = "New",
    numberOfRatings = "15",
    time = "15-30 min",
    distance = "2.73 mi",
)
val restaurant3 = Restaurant(
    id = "3",
    name = "Piece of Chicago Carryout",
    url = "https://www.foodandwine.com/thmb/Wd4lBRZz3X_8qBr69UOu2m7I2iw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/classic-cheese-pizza-FT-RECIPE0422-31a2c938fc2546c9a07b7011658cfd05.jpg",
    rating = "4.2",
    numberOfRatings = "41",
    time = "15-30 min",
    distance = "3.11 mi",
)
val restaurant4 = Restaurant(
    id = "4",
    name = "OH-IO PIZZA",
    url = "https://assets.icanet.se/e_sharpen:80,q_auto,dpr_1.25,w_718,h_718,c_lfill/imagevaultfiles/id_168121/cf_259/pizza_gjord_pa_tortillabrod.jpg",
    rating = "4.4",
    numberOfRatings = "14",
    time = "15-30 min",
    distance = "3.16 mi",
)
val restaurant5 = Restaurant(
    id = "5",
    name = "Borgata Pizza Cafe Budd Dairy",
    url = "https://offloadmedia.feverup.com/secretstockholm.co/wp-content/uploads/2023/08/25171232/1889-1-1024x728.jpg",
    rating = "4.8",
    numberOfRatings = "66",
    time = "15-30 min",
    distance = "4.26 mi",
)
val restaurant6 = Restaurant(
    id = "6",
    name = "Jet's Pizza",
    url = "https://preppykitchen.com/wp-content/uploads/2021/10/Cheese-Pizza-Recipe-Card-500x500.jpg",
    rating = "4.3",
    numberOfRatings = "22",
    time = "15-30 min",
    distance = "4.33 mi",
)
val restaurant7 = Restaurant(
    id = "7",
    name = "Brio Italian Grille",
    url = "https://s23209.pcdn.co/wp-content/uploads/2022/05/Sheet-Pan-Pizza211129_DAMN-DELICIOUS_Sheet-Pan-Pizza_482.jpg",
    rating = "New",
    numberOfRatings = "0",
    time = "30-45 min",
    distance = "4.35 mi",
)
val restaurant8 = Restaurant(
    id = "8",
    name = "Del Baggio Pizzeria",
    url = "https://static.mathem.se/shared/images/recipes/doublelarge/napolitansk-pizza-mozzarella_foto_andrea-klintbjer_mathem.jpeg",
    rating = "4.9",
    numberOfRatings = "41",
    time = "15-30 min",
    distance = "4.41 mi",
)
val restaurant9 = Restaurant(
    id = "9",
    name = "Buca Di Beppo",
    url = "https://www.santamariaworld.com/optimized/recipe-large/globalassets/_recipes/tex-mex/pizza_gronsaker.jpg",
    rating = "New",
    numberOfRatings = "0",
    time = "15-30 min",
    distance = "4.70 mi",
)
val restaurant10 = Restaurant(
    id = "10",
    name = "Piece of Chicago Carryout",
    url = "https://www.seriouseats.com/thmb/1KXAouO18DZnB7JfoaEUhP5z81c=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__2017__02__20170216-detroit-style-pizza-47-1500x1125-1-233d75e6021048b3bf3cf28bd59d310b.jpg",
    rating = "4.2",
    numberOfRatings = "18",
    time = "15-30 min",
    distance = "3.11 mi",
)

val restaurants = listOf(
    restaurant1,
    restaurant2,
    restaurant3,
    restaurant4,
    restaurant5,
    restaurant6,
    restaurant7,
    restaurant8,
    restaurant9,
    restaurant10
)

val group1 = Group(
    id = "1",
    name = "Gluten-free / vegan",
    restaurants = restaurants
)
val group2 = Group(
    id = "2",
    name = "Hot & fresh to slice",
    restaurants = restaurants.sortedBy { it.numberOfRatings }
)
val group3 = Group(
    id = "3",
    name = "Deepest discounts",
    restaurants = restaurants.sortedBy { it.rating }
)
val group4 = Group(
    id = "4",
    name = "Pizza near you",
    restaurants = restaurants.sortedBy { it.name }
)

val groups = listOf(group1, group2, group3, group4)

val menuSubItem1 = MenuSubItem(
    id = "1",
    title = "Cheese Pizza",
    description = "Classic cheese or create your own pizza",
    price = "$10.99"
)
val menuSubItem2 = MenuSubItem(
    id = "2",
    title = "Buckeye Thin Crust Pizza",
    description = null,
    price = "$14.99"
)
val menuSubItem3 = MenuSubItem(
    id = "3",
    title = "Buckeye Thick Crust Pizza",
    description = null,
    price = "$16.99"
)
val menuSubItem4 = MenuSubItem(
    id = "4",
    title = "Buckeye Pizza",
    description = "Pepperoni, sausage, ham, green peppers, onions, mushrooms, extra cheese",
    price = "$13.99"
)
val menuSubItem5 = MenuSubItem(
    id = "5",
    title = "Shrimp Scampi Pizza",
    description = "Mozzarella, cheese, garlic sauce, shrimp & parmesan cheese",
    price = "$13.99"
)
val menuSubItem6 = MenuSubItem(
    id = "6",
    title = "Meat Lovers Pizza",
    description = "Pepperoni, sausage, ham, ground beef, bacon & extra cheese",
    price = "$16.99"
)
val menuSubItem7 = MenuSubItem(
    id = "7",
    title = "Veggie Pizza",
    description = "Choose up to 5 vegetables & extra cheese",
    price = "$16.99"
)
val menuSubItem8 = MenuSubItem(
    id = "8",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItem9 = MenuSubItem(
    id = "9",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItem10 = MenuSubItem(
    id = "10",
    title = "Buckeye Pizza",
    description = "Pepperoni, sausage, ham, green peppers, onions, mushrooms, extra cheese",
    price = "$13.99"
)
val menuSubItem11 = MenuSubItem(
    id = "11",
    title = "Shrimp Scampi Pizza",
    description = "Mozzarella, cheese, garlic sauce, shrimp & parmesan cheese",
    price = "$13.99"
)
val menuSubItem12 = MenuSubItem(
    id = "12",
    title = "Meat Lovers Pizza",
    description = "Pepperoni, sausage, ham, ground beef, bacon & extra cheese",
    price = "$16.99"
)
val menuSubItem13 = MenuSubItem(
    id = "13",
    title = "Veggie Pizza",
    description = "Choose up to 5 vegetables & extra cheese",
    price = "$16.99"
)
val menuSubItem14 = MenuSubItem(
    id = "14",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$16.99"
)
val menuSubItem15 = MenuSubItem(
    id = "15",
    title = "White Pizza",
    description = "Mozzarella, ricotta & parmesan cheese with fresh garlic & Alfredo sauce",
    price = "$13.99"
)

val menuSubItems = listOf(
    menuSubItem1,
    menuSubItem2,
    menuSubItem3,
    menuSubItem4,
    menuSubItem5,
    menuSubItem6,
    menuSubItem7,
    menuSubItem8,
    menuSubItem9,
    menuSubItem10,
    menuSubItem11,
    menuSubItem12,
    menuSubItem13,
    menuSubItem14,
    menuSubItem15
)

val restaurantDetails1 = RestaurantDetails(
    id = "1",
    name = "Buckeye Pizza Express",
    url = "https://res.cloudinary.com/anora/image/upload/t_FoF-HeroFullWidthImage-LG-LG/f_auto/folkofolk%2Fgourmetpizza",
    rating = "4.8",
    numberOfRatings = "19",
    time = "15-30 min",
    distance = "0.61 mi",
    menuItems = listOf(
        MenuItem(
            id = "1",
            title = "Pizza",
            items = listOf(menuSubItem1, menuSubItem2, menuSubItem3)
        ),
        MenuItem(
            id = "2",
            title = "Buckeye Specialty Pizzas",
            items = listOf(
                menuSubItem4,
                menuSubItem5,
                menuSubItem6,
                menuSubItem7,
                menuSubItem8,
                menuSubItem9,
                menuSubItem10,
                menuSubItem11,
                menuSubItem12,
                menuSubItem13,
                menuSubItem14,
                menuSubItem15,
            )
        )
    )
)

val restaurantsDetails = listOf(restaurantDetails1)