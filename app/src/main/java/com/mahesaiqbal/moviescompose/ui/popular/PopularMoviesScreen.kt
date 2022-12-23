package com.mahesaiqbal.moviescompose.ui.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.airbnb.lottie.LottieComposition
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.ui.components.EmptyContent
import com.mahesaiqbal.moviescompose.ui.components.MoviesContent
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel

@Composable
fun PopularMoviesScreen(
    viewModel: MoviesViewModel,
    composition: LottieComposition?,
    animationState: Float,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    viewModel.popularMovies.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getPopularMovies()
            }
            is Resource.Success -> {
                resource.data?.let {
                    if (it.isNotEmpty()) {
                        MoviesContent(
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
