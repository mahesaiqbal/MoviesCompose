package com.mahesaiqbal.moviescompose.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.ui.components.EmptyContent
import com.mahesaiqbal.moviescompose.ui.components.MovieItem
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel
import com.mahesaiqbal.moviescompose.utils.dateFormat

@Composable
fun FavoriteMoviesScreen(
    viewModel: MoviesViewModel,
    composition: LottieComposition?,
    animationState: Float,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    viewModel.favoriteMovies.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getFavoritePopularMovies()
            }
            is Resource.Success -> {
                resource.data?.let {
                    if (it.isNotEmpty()) {
                        FavoriteMoviesContent(
                            movies = it,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail
                        )
                    } else {
                        EmptyContent(
                            composition = composition,
                            animationState = animationState
                        )
                    }
                }
            }
            is Resource.Error -> {
                EmptyContent(
                    composition = composition,
                    animationState = animationState
                )
            }
        }
    }
}

@Composable
fun FavoriteMoviesContent(
    movies: List<PopularMovies>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem(
                title = movie.title,
                posterPath = movie.posterPath,
                releaseDate = dateFormat(movie.releaseDate) ?: "",
                modifier = modifier.clickable {
                    navigateToDetail(movie.id)
                }
            )
        }
    }
}
