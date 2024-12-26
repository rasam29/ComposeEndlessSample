package com.aparat.androidinterview.features.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aparat.androidinterview.R
import com.aparat.androidinterview.ui.views.AparatCircularLoading
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.completePoster
import com.aparat.androidinterview.network.model.toConcatenatedNames
import com.aparat.androidinterview.ui.theme.DetailBackground
import com.aparat.androidinterview.ui.theme.DetailBoxBackground

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    type: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val contentDetailData by viewModel.detailContentData.collectAsStateWithLifecycle()
    Column(
        Modifier
            .background(DetailBackground)
            .padding(horizontal = 24.dp, vertical = 60.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val uiState = contentDetailData) {
            is UiState.Loading -> AparatCircularLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )

            is UiState.Success -> SuccessView(uiState = uiState)

            is UiState.Error -> Error(message = uiState.message)
        }
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier, message: String) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize().then(modifier),
        color = Color.Red
    )
}

@Composable
private fun ColumnScope.SuccessView(
    modifier: Modifier = Modifier,
    uiState: UiState.Success<Content>
) {
    Card(
        modifier = Modifier.weight(3F).then(modifier),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = uiState.data.completePoster(),
                contentDescription = uiState.data.title,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
        }
    }
    ContentDetailBox(uiState = uiState)
    Box(
        modifier = Modifier
            .weight(1F)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = uiState.data.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.5F)
            )

            Text(
                text = uiState.data.voteAverage.toString(),
                color = Color.White.copy(alpha = 0.3F),
                fontSize = 13.sp,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
private fun ColumnScope.ContentDetailBox(
    modifier: Modifier = Modifier,
    uiState: UiState.Success<Content>
) {
    Card(
        modifier = Modifier
            .weight(0.6F)
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(DetailBoxBackground)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.detail_date, uiState.data.date),
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
                Box(
                    modifier = Modifier
                        .background(
                            Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = uiState.data.type.uppercase(),
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(
                    R.string.detail_genre_place_holder,
                    uiState.data.genres.toConcatenatedNames()
                ),
                color = Color.LightGray,
                fontSize = 13.sp
            )
        }
    }

}