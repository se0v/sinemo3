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
        startDestination = "mainscreen"
    ){
        composable(
            route = "mainscreen"
        ){
            LazyListScreen()
        }
        composable(
            route = "lazylist"
        ){
            MainScreen()
        }
    }
    
}