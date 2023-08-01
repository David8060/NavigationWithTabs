package com.example.composenavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


sealed class Screen(val route: String) {
    object TabHost : Screen("tabs")
    object Tab1 : Screen("tab1")
    object Tab2 : Screen("tab2")
    object Tab3 : Screen("tab3")
    object NestedRouteScreen : Screen("nestedRoute")
}

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TabHost.route,
    ) {
        composable(Screen.TabHost.route) { TabHost(navController) }
        composable(Screen.Tab1.route) {
            Tab1()
        }
        composable(Screen.Tab2.route) {
            Tab2()
        }
        composable(Screen.Tab3.route) {
            Tab3(navController)
        }
        composable(Screen.NestedRouteScreen.route) {
            NestedRouteScreen()
        }
    }
}