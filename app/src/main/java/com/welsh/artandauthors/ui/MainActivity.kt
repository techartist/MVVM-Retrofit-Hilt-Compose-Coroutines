package com.welsh.artandauthors.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.welsh.artandauthors.navigation.NavGraph
import com.welsh.artandauthors.ui.imagelist.ImagesScreen
import com.welsh.artandauthors.ui.theme.ArtAndAuthorsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtAndAuthorsTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtAndAuthorsTheme {
        val navController = rememberNavController()
        ImagesScreen(navController)
    }
}