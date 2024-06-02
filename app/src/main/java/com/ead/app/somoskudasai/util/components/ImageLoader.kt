package com.ead.app.somoskudasai.util.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url : String,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription : String? = null,
    builder: ImageRequest.Builder.() -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = url).apply(block = builder).build()
    )
    Image(
        modifier = modifier,
        painter = painter,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}