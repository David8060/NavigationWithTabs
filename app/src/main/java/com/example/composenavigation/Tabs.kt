package com.example.composenavigation

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

sealed class TabScreen(val route: String) {
    object Tab1 : TabScreen("tab1")
    object Tab2 : TabScreen("tab2")
    object Tab3 : TabScreen("tab3")
}

@Composable
fun Tab1() {
    Text("Tab 1 Content")
}

@Composable
fun Tab2() {
    Text("Tab 2 Content")
}


@Composable
fun Tab3(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(Screen.Tab1.route) {
            popUpTo("tabs") {
                inclusive = true
            }
        } }) {
            Text("Navigate to tab 1")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            navController.navigate(Screen.NestedRouteScreen.route)

        }) {
            Text("Navigate to nested route")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Click here to close the app",
            color = Color.Blue,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable { showDialog = true }
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmation") },
                text = { Text("Are you sure you want to close the app?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            activity?.finish()
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}