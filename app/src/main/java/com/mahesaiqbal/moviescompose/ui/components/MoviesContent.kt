package com.mahesaiqbal.moviescompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies

@Composable
fun MoviesContent(
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
                releaseDate = movie.releaseDate,
                modifier = modifier.clickable {
                    navigateToDetail(movie.id)
                }
            )
        }
    }
}
