package com.aparat.androidinterview.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aparat.androidinterview.features.home.more.MoreScreen
import com.aparat.androidinterview.features.home.movies.view.MovieScreen
import com.aparat.androidinterview.features.home.tvShows.view.TVShowsScreen
import com.aparat.androidinterview.ui.navigation.Screen
import com.aparat.androidinterview.ui.views.AparatNavigationBar
import com.aparat.androidinterview.ui.views.AparatToolbar
import com.aparat.androidinterview.ui.views.bottomBarItems

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val selectedPage = rememberSaveable { mutableIntStateOf(0) }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        AparatToolbar(title = stringResource(id = bottomBarItems[selectedPage.intValue].completeLabel),
            searchClick = {
                navController.navigate(Screen.SearchScreen.route + "/${bottomBarItems[selectedPage.intValue].contentType}")
            })
    }, bottomBar = {
        AparatNavigationBar(selectedIndex = selectedPage.intValue, onSelect = { index ->
            selectedPage.intValue = index
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.Black)
                .fillMaxSize()
        ) {
            HomeTabs(
                pageIndex = selectedPage.intValue, navController = navController
            )
        }
    }
}


@Composable
private fun HomeTabs(modifier: Modifier = Modifier, pageIndex: Int, navController: NavController) {
    val tvShowGridState = rememberLazyGridState()
    val movieGridState = rememberLazyGridState()
    when (pageIndex) {
        0 -> MovieScreen(modifier = modifier, navController = navController, gridState = movieGridState)
        1 -> TVShowsScreen(modifier = modifier, navController = navController, gridState = tvShowGridState)
        2 -> MoreScreen(modifier = modifier, navController = navController)
    }
}