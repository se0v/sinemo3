package com.example.sinemo.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sinemo.R

sealed class AppScreen(
    val route: String,
    @StringRes val nameResource: Int,
    val icon: ImageVector
){
    object LazyList: AppScreen("lazyList", R.string.screen_lazy_list, Icons.Default.List)
    object MainScreen: AppScreen("mainScreen", R.string.screen_main, Icons.Default.Home)
    object PermissionScreen: AppScreen("permScreen", R.string.screen_perm ,Icons.Default.Warning)

    companion object {
        fun getAll() = listOf(
            MainScreen,
            PermissionScreen,
            LazyList
        )
    }
}
