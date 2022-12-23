package com.mahesaiqbal.moviescompose.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel

@Composable
fun DetailMovieScreen(
    movieId: Int,
    viewModel: MoviesViewModel,
    navigateBack: () -> Unit
) {

}

@Composable
fun DetailMovieContent(
    detailMovie: PopularMovies,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
        ) {
//            AsyncImage(model = , contentDescription = )
        }
    }
}
