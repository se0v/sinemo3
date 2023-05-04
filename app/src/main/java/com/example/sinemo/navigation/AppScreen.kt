package com.example.sinemo.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sinemo.R

sealed class AppScreen(
    val route: String,
    @StringRes val nameResource: Int,
    val icon: ImageVector
){
    object LazyList: AppScreen("lazylist", R.string.screen_lazy_list, Icons.Default.Add)
    object RecyclerView: AppScreen("recyclerview", R.string.screen_main, Icons.Default.Home)

    companion object {
        fun getAll() = listOf(
            LazyList,
            RecyclerView
        )
    }
}
