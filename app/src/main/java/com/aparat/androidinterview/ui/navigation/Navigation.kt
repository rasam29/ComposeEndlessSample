package com.aparat.androidinterview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aparat.androidinterview.CONTENT_ID
import com.aparat.androidinterview.TYPE
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.features.detail.view.DetailScreen
import com.aparat.androidinterview.features.home.HomeScreen
import com.aparat.androidinterview.features.search.view.SearchScreen

sealed class Screen(val route: String) {
    data object SearchScreen : Screen("search_screen")
    data object DetailScreen : Screen("detail_screen")
    data object HomeScreen : Screen("main_screen")
}

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            Screen.DetailScreen.route + "/{$CONTENT_ID}/{$TYPE}",
            arguments = listOf(navArgument(CONTENT_ID) { type = NavType.IntType },
                navArgument(TYPE) { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(CONTENT_ID) ?: -1
            val type = backStackEntry.arguments?.getString(TYPE) ?: TYPE_MOVIE
            DetailScreen(
                navController, id = id, type = type
            )
        }

        composable(
            Screen.SearchScreen.route + "/{$TYPE}",
            arguments = listOf(navArgument(TYPE) { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString(TYPE) ?: TYPE_MOVIE
            SearchScreen(navController = navController, type = type)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}