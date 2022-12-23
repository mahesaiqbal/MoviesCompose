package com.mahesaiqbal.moviescompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mahesaiqbal.moviescompose.ui.about.AboutScreen
import com.mahesaiqbal.moviescompose.ui.components.BottomBar
import com.mahesaiqbal.moviescompose.ui.components.SearchBar
import com.mahesaiqbal.moviescompose.ui.detail.DetailMovieScreen
import com.mahesaiqbal.moviescompose.ui.favorite.FavoriteMoviesScreen
import com.mahesaiqbal.moviescompose.ui.navigation.Screen
import com.mahesaiqbal.moviescompose.ui.popular.PopularMoviesScreen
import com.mahesaiqbal.moviescompose.ui.theme.MoviesComposeTheme
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesApp(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val movieTitle by viewModel.title

    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset("emptybox.json"))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = Int.MAX_VALUE
    )

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Popular.route) {
                SearchBar(
                    query = movieTitle,
                    onQueryChange = viewModel::searchMovies,
                    modifier = modifier.background(MaterialTheme.colors.primary)
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.DetailMovie.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Popular.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Popular.route) {
                PopularMoviesScreen(
                    viewModel = viewModel,
                    composition = lottieComposition,
                    animationState = lottieProgress,
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailMovie.createRoute(id))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteMoviesScreen(
                    viewModel = viewModel,
                    composition = lottieComposition,
                    animationState = lottieProgress,
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailMovie.createRoute(id))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailMovieScreen(
                    movieId = id,
                    viewModel = viewModel,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesAppPreview() {
    MoviesComposeTheme {
        MoviesApp()
    }
}
