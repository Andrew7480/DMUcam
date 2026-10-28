package com.movil.dm_ucam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movil.dm_ucam.ui.home.HomeScreen
import com.movil.dm_ucam.ui.login.LoginScreen

@Composable
fun AppNavGraph(
    isLoggedIn: Boolean = false
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { name, gender, montana, sprint ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("name", name)
                    navController.currentBackStackEntry?.savedStateHandle?.set("gender", gender)
                    navController.currentBackStackEntry?.savedStateHandle?.set("montana", montana)
                    navController.currentBackStackEntry?.savedStateHandle?.set("sprint", sprint)

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            val backStackEntry = navController.previousBackStackEntry
            val name = backStackEntry?.savedStateHandle?.get<String>("name") ?: "Usuario"
            val gender = backStackEntry?.savedStateHandle?.get<String>("gender") ?: "Otro"
            val montana = backStackEntry?.savedStateHandle?.get<Int>("montana") ?: 50
            val sprint = backStackEntry?.savedStateHandle?.get<Int>("sprint") ?: 50

            HomeScreen(
                name = name,
                gender = gender,
                montana = montana,
                sprint = sprint,
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
