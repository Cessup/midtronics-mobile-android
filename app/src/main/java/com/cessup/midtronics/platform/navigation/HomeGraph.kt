package com.cessup.midtronics.platform.navigation

/**
 * The HomeGraph is a component with the routes
 *
 * @constructor route that is the reference to use in the Graph
 *
 * @author
 *     Cessup
 * @since 1.0
 */
sealed class HomeGraph(val route: String) {
    object Root : HomeGraph("home")

    object Home : HomeGraph("home/start")

    object CountryDetails : HomeGraph("home/countries/details"){
        const val routeWithArgs = "home/countries/details/{COUNTRY_NAME}"
        fun withArgs(countryName: String) = "home/countries/details/$countryName"
    }

    enum class DataShare{
        COUNTRY_NAME
    }
}