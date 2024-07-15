package com.littlelemon.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(context: Context, navHostController: NavHostController) {
    val sharedPref = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val startDestination = if(sharedPref.getString("email", "")!!.isEmpty()) {
        Onboarding.route
    } else {
        Home.route
    }

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Home.route) {
            Home(navHostController)
        }
        composable(Profile.route) {
            Profile(context, navHostController)
        }
        composable(Onboarding.route) {
            Onboarding(context = context, navController = navHostController)
        }
    }
}