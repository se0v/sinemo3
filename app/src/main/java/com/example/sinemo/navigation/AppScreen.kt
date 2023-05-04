package com.example.sinemo.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sinemo.R

sealed class AppScreen(
    val route: String,
    @StringRes val nameResource: Int,
    val icon: ImageVector
){
    object LazyList: AppScreen("lazylist", R.string.screen_lazy_list, Icons.Default.List)
    object MainScreen: AppScreen("mainscreen", R.string.screen_main, Icons.Default.Home)

    companion object {
        fun getAll() = listOf(
            MainScreen,
            LazyList
        )
    }
}
