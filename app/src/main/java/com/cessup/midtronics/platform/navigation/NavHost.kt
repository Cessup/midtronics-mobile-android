package com.cessup.midtronics.platform.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cessup.midtronics.platform.ui.countries.DetailsCountryScreen
import com.cessup.midtronics.platform.ui.home.HomeScreen
import com.cessup.midtronics.platform.ui.network.NetworkErrorScreen
import com.cessup.midtronics.platform.ui.profile.ProfileScreen

/**
 * The NavHost in Compose
 *
 * There are all graphs about every flow in the application
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeGraph.Root.route) {
        generalNavGraph(navController)
        homeNavGraph(navController)
    }
}

/**
 * generalNavGraph is the function navigate in home flow
 *
 * @param navController the navController is the view control
 *
 */
fun NavGraphBuilder.generalNavGraph(navController: NavController) {
    navigation(
        route = GeneralGraph.Root.route,
        startDestination = GeneralGraph.NetworkError.route
    ) {
        composable(
            route = GeneralGraph.NetworkError.routeWithArgs,
            arguments = listOf(
                navArgument(GeneralGraph.DataShare.ERROR_VALUE.name) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val error = backStackEntry.arguments?.getString(GeneralGraph.DataShare.ERROR_VALUE.name) ?: ""
            NetworkErrorScreen(error = error)
        }
    }
}

/**
 * homeNavGraph is the function navigate in home flow
 *
 * @param navController the navController is the view control
 *
 */
fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        route = HomeGraph.Root.route,
        startDestination = HomeGraph.Home.route
    ) {
        composable(HomeGraph.Home.route) {
            HomeScreen(
                onActionCountriesList = {
                    navController.navigate(HomeGraph.CountryDetails.withArgs(countryName = it))
                },
                onNavNetworkError = {
                    navController.navigate(GeneralGraph.NetworkError.withArgs(error= it))
                },
                onNavProfile = {
                    navController.navigate(HomeGraph.Profile.route)
                }
            )

        }

        composable(
            route = HomeGraph.CountryDetails.routeWithArgs,
            arguments = listOf(
                navArgument(HomeGraph.DataShare.COUNTRY_NAME.name) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val country = backStackEntry.arguments?.getString(HomeGraph.DataShare.COUNTRY_NAME.name) ?: ""
            DetailsCountryScreen(
                nameCountry = country,
                onNavNetworkError = {
                    navController.navigate(GeneralGraph.NetworkError.withArgs(error= it))
                },
                onNavProfile = {
                    navController.navigate(HomeGraph.Profile.route)
                })
        }

        composable (route= HomeGraph.Profile.route){
            ProfileScreen(onNavNetworkError = {
                navController.navigate(GeneralGraph.NetworkError.withArgs(error= it))
            })
        }
    }
}