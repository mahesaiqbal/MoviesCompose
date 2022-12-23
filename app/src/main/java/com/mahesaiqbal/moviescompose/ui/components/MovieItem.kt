package com.mahesaiqbal.moviescompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mahesaiqbal.moviescompose.R
import com.mahesaiqbal.moviescompose.ui.theme.MoviesComposeTheme
import com.mahesaiqbal.moviescompose.ui.theme.Shapes

@Composable
fun MovieItem(
    title: String,
    posterPath: String,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = modifier,
        content = {
            Column(
                modifier = Modifier
            ) {
                AsyncImage(
                    model = stringResource(R.string.base_image_url, posterPath),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .clip(Shapes.small)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                    )
                )
                Text(
                    text = stringResource(R.string.release_date_movie, releaseDate),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MoviesComposeTheme {
        MovieItem(
            title = "Judul Film",
            posterPath = "",
            releaseDate = "21 September 2022"
        )
    }
}
