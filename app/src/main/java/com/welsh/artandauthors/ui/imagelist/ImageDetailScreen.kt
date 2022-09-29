package com.welsh.artandauthors.ui.imagelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.welsh.artandauthors.data.domain.Image
import com.welsh.artandauthors.navigation.Screen

@Composable
fun ImageDetailScreen(authorName: String, download_url: String) {

    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(
        remember(download_url) {
            ImageRequest.Builder(context)
                .data(download_url)
                .diskCacheKey(download_url)
                .memoryCacheKey(download_url)
                .build()
        }
    )

    Column(verticalArrangement = Arrangement.Top) {
        Text(
            text = authorName,
            color = Color.Black,
            modifier = Modifier
                .background(Color.DarkGray.copy(alpha = 0.5f))
                .fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painter,
            contentDescription = authorName,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(CenterHorizontally)
                .clip(RoundedCornerShape(10.dp)),

        )
    }
}

