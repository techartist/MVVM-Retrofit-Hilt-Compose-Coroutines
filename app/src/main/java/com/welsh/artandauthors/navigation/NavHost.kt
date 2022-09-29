package com.welsh.artandauthors.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.welsh.artandauthors.ui.imagelist.ImageDetailScreen
import com.welsh.artandauthors.ui.imagelist.ImagesScreen


@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.MyListScreen.route)
    {
        composable(route = Screen.MyListScreen.route){
            ImagesScreen(navController = navController)
        }
         composable(
         route = Screen.MyDetailScreen.route +
                 "?authorName={authorName},download_url={download_url}",
             listOf(
                 navArgument("authorName") {
                     type = NavType.StringType
                 },
                 navArgument("download_url") {
                     type = NavType.StringType
                 },
            )
        ) { backStackEntry ->
             val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
             val downloadUrl = backStackEntry.arguments?.getString("download_url") ?: ""
             ImageDetailScreen(authorName = authorName, download_url = downloadUrl)
        }

    }
}