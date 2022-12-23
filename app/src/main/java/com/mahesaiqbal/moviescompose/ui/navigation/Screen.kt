package com.mahesaiqbal.moviescompose.ui.navigation

sealed class Screen(val route: String) {
    object Popular : Screen("popular")
    object Bookmark : Screen("bookmark")
    object About : Screen("about")
    object DetailMovie : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}
