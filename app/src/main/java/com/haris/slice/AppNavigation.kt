package com.haris.slice

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.haris.account.Account
import com.haris.grouprestaurants.ui.GroupRestaurants
import com.haris.home.ui.Home
import com.haris.orders.Orders
import com.haris.restaurantdetails.RestaurantDetails
import com.haris.rewards.Rewards
import com.haris.search.ui.Search

internal sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Orders : Screen("orders")
    data object Rewards : Screen("rewards")
    data object Account : Screen("account")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    data object Home : LeafScreen("home")
    data object Search : LeafScreen("search")
    data object Orders : LeafScreen("orders")
    data object Rewards : LeafScreen("rewards")
    data object Account : LeafScreen("account")

    data object RestaurantDetails : LeafScreen("restaurant_details/{id}") {
        fun createRoute(root: Screen, id: String): String {
            return "${root.route}/restaurant_details/$id"
        }
    }

    data object GroupRestaurants : LeafScreen(
        "group_restaurants/{id}/optional_arguments?name={name}"
    ) {
        fun createRoute(root: Screen, id: String, name: String): String {
            return "${root.route}/group_restaurants/$id/optional_arguments?name=$name"
        }
    }
}

@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        exitTransition = { defaultExitTransition(initialState, targetState) },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
        modifier = modifier,
    ) {
        addHomeTopLevel(navController)
        addSearchTopLevel(navController)
        addOrdersTopLevel()
        addRewardsTopLevel()
        addAccountTopLevel()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHomeTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Home.route,
        startDestination = LeafScreen.Home.createRoute(Screen.Home),
    ) {
        addHome(navController, Screen.Home)
        addRestaurantDetails(navController, Screen.Home)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addSearchTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Search.route,
        startDestination = LeafScreen.Search.createRoute(Screen.Search),
    ) {
        addSearch(navController, Screen.Search)
        addRestaurantDetails(navController, Screen.Search)
        addGroupRestaurants(navController, Screen.Search)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addOrdersTopLevel() {
    navigation(
        route = Screen.Orders.route,
        startDestination = LeafScreen.Orders.createRoute(Screen.Orders),
    ) {
        addOrders(Screen.Orders)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addRewardsTopLevel() {
    navigation(
        route = Screen.Rewards.route,
        startDestination = LeafScreen.Rewards.createRoute(Screen.Rewards),
    ) {
        addRewards(Screen.Rewards)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAccountTopLevel() {
    navigation(
        route = Screen.Account.route,
        startDestination = LeafScreen.Account.createRoute(Screen.Account),
    ) {
        addAccount(Screen.Account)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHome(
    navController: NavController,
    root: Screen,
) {
    composable(
        route = LeafScreen.Home.createRoute(root)
    ) {
        Home {
            navController.navigate(
                LeafScreen.RestaurantDetails.createRoute(root, it)
            )
        }
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addSearch(
    navController: NavController,
    root: Screen,
) {
    composable(
        route = LeafScreen.Search.createRoute(root)
    ) {
        Search(
            navigate = {
                navController.navigate(LeafScreen.RestaurantDetails.createRoute(root, it))
            },
            navigateToGroupRestaurants = { id, name ->
                navController.navigate(
                    LeafScreen.GroupRestaurants.createRoute(root, id, name)
                )
            }
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addOrders(
    root: Screen,
) {
    composable(
        route = LeafScreen.Orders.createRoute(root)
    ) {
        Orders()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addRewards(
    root: Screen,
) {
    composable(
        route = LeafScreen.Rewards.createRoute(root)
    ) {
        Rewards()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAccount(
    root: Screen,
) {
    composable(
        route = LeafScreen.Account.createRoute(root)
    ) {
        Account()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addRestaurantDetails(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.RestaurantDetails.createRoute(root),
        arguments = listOf(
            navArgument("id") { type = NavType.StringType },
        ),
    ) {
        RestaurantDetails(navController::navigateUp)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addGroupRestaurants(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.GroupRestaurants.createRoute(root),
        arguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("name") { type = NavType.StringType },
        ),
    ) {
        GroupRestaurants(
            navigateUp = navController::navigateUp
        ) {
            navController.navigate(
                LeafScreen.RestaurantDetails.createRoute(root, it)
            )
        }
    }
}

@ExperimentalAnimationApi
private fun AnimatedContentTransitionScope<*>.defaultEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): EnterTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeIn()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeIn() + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentTransitionScope<*>.defaultExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry,
): ExitTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeOut()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeOut() + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
}

private val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph

@ExperimentalAnimationApi
private fun AnimatedContentTransitionScope<*>.defaultPopEnterTransition(): EnterTransition {
    return fadeIn() + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)
}

@ExperimentalAnimationApi
private fun AnimatedContentTransitionScope<*>.defaultPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
}
