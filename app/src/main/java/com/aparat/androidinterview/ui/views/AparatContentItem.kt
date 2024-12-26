package com.aparat.androidinterview.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aparat.androidinterview.R
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.completeThumbnailUrl
import com.aparat.androidinterview.network.model.parenthesisedVoteAverage


@Composable
fun AparatContentItem(modifier: Modifier = Modifier, content: Content, onClick: (Int) -> Unit) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            Modifier
                .background(Color.DarkGray)
                .padding(bottom = 4.dp)
                .clickable { onClick.invoke(content.id) }) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.65F)
                    .clip(RoundedCornerShape(topEnd = 4.dp, topStart = 4.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(content.completeThumbnailUrl())
                    .crossfade(true)
                    .build(),
                contentDescription = content.title,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
            Text(
                text = content.title,
                maxLines = 2,
                minLines = 2,
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 13.sp),
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 4.dp, top = 8.dp, end = 4.dp)
                    .fillMaxWidth()
            )
            Row(Modifier.padding(start = 4.dp, top = 8.dp, end = 4.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    color = Color.LightGray,
                    text = content.date,
                    style = TextStyle(fontSize = 11.sp)
                )
                Text(
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.LightGray,
                    text = content.parenthesisedVoteAverage(),
                    style = TextStyle(fontSize = 11.sp)
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewItem() {
    AparatContentItem(
        content = Content.MovieResponse(
            12,
            "salamssssss",
            "sdsd",
            "https://picsum.photos/200/300",
            0F,
            listOf()
        ), onClick = {})
}

