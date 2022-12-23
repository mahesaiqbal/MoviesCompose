package com.mahesaiqbal.moviescompose.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mahesaiqbal.moviescompose.R
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel
import com.mahesaiqbal.moviescompose.utils.dateFormat

@Composable
fun DetailMovieScreen(
    movieId: Int,
    viewModel: MoviesViewModel,
    navigateBack: () -> Unit
) {
    viewModel.detailMovie.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getDetailMovie(movieId)
            }
            is Resource.Success -> {
                resource.data?.let { detailMovie ->
                    DetailMovieContent(
                        isFavorite = detailMovie.isFavorite,
                        posterPath = detailMovie.posterPath,
                        title = detailMovie.title,
                        popularity = detailMovie.popularity.toInt().toString(),
                        voteAverage = detailMovie.voteAverage.toString(),
                        voteCount = detailMovie.voteCount.toString(),
                        releaseDate = dateFormat(detailMovie.releaseDate) ?: "",
                        overview = detailMovie.overview,
                        navigateBack = navigateBack,
                        favoriteClick = { newState ->
                            viewModel.setFavoritePopularMovie(detailMovie, newState)
                        }
                    )
                }
            }
            is Resource.Error -> {
                // Do something
            }
        }
    }
}

@Composable
fun DetailMovieContent(
    isFavorite: Boolean,
    posterPath: String,
    title: String,
    popularity: String,
    voteAverage: String,
    voteCount: String,
    releaseDate: String,
    overview: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    favoriteClick: (Boolean) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { favoriteClick(!isFavorite) },
                contentColor = if (isFavorite) Color.Red else Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.favorite_movie)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                AsyncImage(
                    model = stringResource(R.string.base_image_url_for_detail, posterPath),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(600.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                )
                OutlinedButton(
                    onClick = { navigateBack() },
                    shape = CircleShape,
                    contentPadding = innerPadding,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 72.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.Blue
                        )
                        Text(
                            text = popularity,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = voteAverage,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = null,
                            tint = Color.Green
                        )
                        Text(
                            text = voteCount,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(
                        text = releaseDate,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(
                    text = overview,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
