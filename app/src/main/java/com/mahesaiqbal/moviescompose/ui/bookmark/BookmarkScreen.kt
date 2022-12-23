package com.mahesaiqbal.moviescompose.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.airbnb.lottie.LottieComposition
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.ui.components.EmptyContent
import com.mahesaiqbal.moviescompose.ui.components.MoviesContent
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel

@Composable
fun BookmarkScreen(
    viewModel: MoviesViewModel,
    composition: LottieComposition?,
    animationState: Float,
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
                        MoviesContent(
                            movies = it,
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