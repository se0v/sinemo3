package com.example.sinemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(
    navController : NavHostController
){
    
    NavHost(
        navController = navController,
        startDestination = ""
    ){

    }
    
}