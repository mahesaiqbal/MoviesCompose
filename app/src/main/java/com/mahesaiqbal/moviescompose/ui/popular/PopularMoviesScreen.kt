package com.mahesaiqbal.moviescompose.ui.popular

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.mahesaiqbal.moviescompose.R
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.ui.components.MovieItem
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel

@Composable
fun PopularMoviesScreen(
    viewModel: MoviesViewModel,
    composition: LottieComposition?,
    animationState: Float,
    navigateToDetail: (Int) -> Unit
) {
//    LaunchedEffect(Unit) {
//        viewModel.getPopularMovies()
//    }
    
    viewModel.popularMovies.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getPopularMovies()
            }
            is Resource.Success -> {
                resource.data?.let {
                    if (it.isNotEmpty()) {
                        PopularMoviesContent(
                            popularMovies = it,
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
fun EmptyContent(
    composition: LottieComposition?,
    animationState: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { animationState },
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.try_again_later),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PopularMoviesContent(
    popularMovies: List<PopularMovies>,
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
        items(popularMovies) { popularMovie ->
            MovieItem(
                title = popularMovie.title,
                posterPath = popularMovie.posterPath,
                releaseDate = popularMovie.releaseDate,
                modifier = modifier.clickable {
                    navigateToDetail(popularMovie.id)
                }
            )
        }
    }
}
