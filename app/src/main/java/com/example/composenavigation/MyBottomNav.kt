package com.example.composenavigation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun MyBottomNav() {
    val navController = rememberNavController()
    val tabRoutes = listOf(TabScreen.Tab1.route, TabScreen.Tab2.route, TabScreen.Tab3.route)

    val selectedTabIndex = remember { mutableStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: tabRoutes[0]
    selectedTabIndex.value = tabRoutes.indexOf(currentRoute)

    BottomNavigation(
        modifier = Modifier.systemBarsPadding(),
        backgroundColor = Color.Green
    ) {
        tabRoutes.forEachIndexed { index, route ->
            val selectedColor = if (selectedTabIndex.value == index) Color.Black else Color.Gray

            BottomNavigationItem(
                selected = selectedTabIndex.value == index,
                onClick = {
                    if (selectedTabIndex.value != index) {
                        selectedTabIndex.value = index
                        navController.navigate(route) {
                        }
                    }
                },
                icon = {},
                label = {
                    Text(
                        text = "Tab ${index + 1}",
                        style = TextStyle.Default.copy(fontWeight = FontWeight.Bold),
                        color = selectedColor
                    )
                }
            )
        }
    }
    LaunchedEffect(navController) {
        val initialRoute = tabRoutes[selectedTabIndex.value]
        navController.navigate(initialRoute)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 54.dp)
        //  .navigationBarsPadding() doesn't work
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun TabHost(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedTabIndex = when (navBackStackEntry?.destination?.route ?: TabScreen.Tab1.route) {
        TabScreen.Tab1.route -> 0
        TabScreen.Tab2.route -> 1
        TabScreen.Tab3.route -> 2
        else -> 0
    }

    when (selectedTabIndex) {
        0 -> TabScreen.Tab1
        1 -> TabScreen.Tab2
        2 -> TabScreen.Tab3
    }
}





