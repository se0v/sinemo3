package com.example.sinemo.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sinemo.screens.LazyListScreen
import com.example.sinemo.screens.MainScreen
import com.example.sinemo.screens.PermissionScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavigation(
    navController: NavHostController
) {
    val items = listOf(
        AppScreen.PermissionScreen,
        AppScreen.MainScreen,
        AppScreen.LazyList
    )

    val selectedItem = remember { mutableStateOf(AppScreen.MainScreen) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.nameResource)) },
                        selected = selectedItem.value == screen,
                        onClick = { selectedItem.value = screen as AppScreen.MainScreen }
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(navController, startDestination = AppScreen.MainScreen.route) {
                items.forEach { screen ->
                    composable(route = screen.route) {
                        when (screen) {
                            AppScreen.LazyList -> LazyListScreen()
                            AppScreen.PermissionScreen -> PermissionScreen()
                            AppScreen.MainScreen -> MainScreen()
                        }
                    }
                }
            }
        }
    }
}