package com.welsh.artandauthors.ui.imagelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.welsh.artandauthors.ImageCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesScreen(navController: NavController, viewModel: ImagesViewModel = hiltViewModel()) {
    val images = viewModel.uiStateImages.collectAsState()
    val error = viewModel.error.collectAsState()
    val loading = viewModel.loading.collectAsState()
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when {
            (error.value.isNotEmpty()) -> {
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = error.value, color = Color.Red, style = TextStyle(fontSize = 24.sp))
            }
            loading.value -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier
                        .width(50.dp)
                        .height(50.dp))
                }
            }
            else -> {
                LazyColumn {
                    items(images.value) { image ->
                        ImageCard(image = image, navController = navController)
                    }
                }
            }
        }
    }
}