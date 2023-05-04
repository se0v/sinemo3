package com.example.sinemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sinemo.screens.LazyListScreen
import com.example.sinemo.screens.MainScreen

@Composable
fun AppNavigation(
    navController : NavHostController
){
    
    NavHost(
        navController = navController,
        startDestination = AppScreen.LazyList.route
    ){
        composable(
            route = AppScreen.LazyList.route
        ){
            LazyListScreen()
        }
        composable(
            route = AppScreen.MainScreen.route
        ){
            MainScreen()
        }
    }
    
}