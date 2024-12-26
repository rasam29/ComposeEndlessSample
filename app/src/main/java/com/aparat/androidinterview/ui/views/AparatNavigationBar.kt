package com.aparat.androidinterview.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aparat.androidinterview.R
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.TYPE_TV_SHOWS
import com.aparat.androidinterview.ui.theme.Purple40

@Composable
fun AparatNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    NavigationBar(containerColor = Color.DarkGray, modifier = modifier) {
        AparatBottomBarItems(onSelect, selectedIndex)
    }
}

@Composable
private fun RowScope.AparatBottomBarItems(onSelect: (Int) -> Unit, selectedIndex: Int) {
    bottomBarItems.forEachIndexed { index, item ->
        AparatNavigationBarItem(
            index = index,
            icon = item.icon,
            label = item.label,
            isSelected = index == selectedIndex,
            onClick = {
                onSelect(index)
            }
        )
    }
}

@Composable
private fun RowScope.AparatNavigationBarItem(
    modifier: Modifier = Modifier,
    index: Int,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    isSelected: Boolean,
    onClick: (Int) -> Unit
) {
    NavigationBarItem(selected = isSelected, onClick = { onClick.invoke(index) }, label = {
        Text(
            color = if (isSelected) Purple40 else Color.White,
            text = stringResource(id = label),
            style = TextStyle(
                fontSize = if (isSelected) 15.sp else 13.sp
            )
        )
    }, modifier = modifier, icon = {
        Icon(
            painterResource(id = icon),
            stringResource(id = label),
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Purple40 else Color.White
        )
    })
}

val bottomBarItems = listOf(
    BottomBarItems(
        TYPE_MOVIE,
        R.drawable.movie,
        R.string.navigation_bar_movies,
        R.string.toolbar_bar_movies,
    ), BottomBarItems(
        TYPE_TV_SHOWS,
        R.drawable.shows,
        R.string.navigation_bar_shows,
        R.string.toolbar_bar_shows,
    ), BottomBarItems(
        "",
        R.drawable.more,
        R.string.navigation_bar_more,
        R.string.navigation_bar_more
    )
)

data class BottomBarItems(
    val contentType: String,
    val icon: Int,
    val label: Int,
    val completeLabel: Int
)

@Preview
@Composable
fun PreviewNavigationBar() {
    AparatNavigationBar(selectedIndex = -1, onSelect = {})
}