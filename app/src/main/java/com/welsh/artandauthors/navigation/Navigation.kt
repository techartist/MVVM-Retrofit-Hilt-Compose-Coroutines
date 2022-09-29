package com.welsh.artandauthors.navigation

sealed class Screen(val route:String){
    object MyListScreen:Screen("my_list_screen")
    object MyDetailScreen:Screen("my_detail_screen")
}