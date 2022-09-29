package com.welsh.artandauthors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.disk.DiskCache
import coil.request.ImageRequest
import com.welsh.artandauthors.data.domain.Image
import com.welsh.artandauthors.navigation.Screen

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ImageCard(image: Image, navController: NavController){

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier
            .size(170.dp, 170.dp)
            .padding(16.dp)
            .clickable {
                navController.navigate(
                    Screen.MyDetailScreen.route +
                            "?authorName= ${image.author},download_url=${image.downloadUrl}"
                )
            }
    ) {
        Box(modifier = Modifier
            .size(170.dp)){
            SubcomposeAsyncImage(
                model = image.downloadUrl + "/",
                loading = {
                    CircularProgressIndicator(modifier = Modifier.height(10.dp).width(10.dp))
                },
                contentDescription = image.author,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = image.author ?: "",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.DarkGray.copy(alpha = 0.5f))
                    .fillMaxWidth(),
                fontSize = TextUnit(value = 20f, TextUnitType.Sp),
                textAlign = TextAlign.Center
            )
        }
    }
}