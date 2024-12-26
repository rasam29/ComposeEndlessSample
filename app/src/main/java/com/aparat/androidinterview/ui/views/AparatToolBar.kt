package com.aparat.androidinterview.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aparat.androidinterview.R
import com.aparat.androidinterview.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AparatToolbar(
    modifier: Modifier = Modifier,
    title: String,
    hasIcon: Boolean = true,
    searchClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                title, color = Color.White, style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        actions = {
            if (hasIcon) {
                IconButton(onClick = searchClick) {
                    Icon(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(id = R.string.search_icon_description),
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
@Preview
private fun PreviewToolbar() {
    AparatToolbar(title = "salam", searchClick = {})
}