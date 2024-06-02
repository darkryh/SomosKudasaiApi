package com.ead.app.somoskudasai

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.transform.CircleCropTransformation
import com.ead.app.somoskudasai.main.MainViewModel
import com.ead.app.somoskudasai.main.theme.SomosKudasaiTheme
import com.ead.app.somoskudasai.util.components.ImageLoader
import com.ead.lib.somoskudasai.models.News
import com.ead.lib.somoskudasai.models.html_tags.H1
import com.ead.lib.somoskudasai.models.html_tags.H2
import com.ead.lib.somoskudasai.models.html_tags.H3
import com.ead.lib.somoskudasai.models.html_tags.H4
import com.ead.lib.somoskudasai.models.html_tags.H5
import com.ead.lib.somoskudasai.models.html_tags.Iframe
import com.ead.lib.somoskudasai.models.html_tags.Image
import com.ead.lib.somoskudasai.models.html_tags.P
import com.ead.lib.somoskudasai.models.html_tags.Ul
import com.ead.lib.somoskudasai.models.html_tags.Video

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SomosKudasaiTheme {
                LaunchedEffect(true) {
                    viewModel.onEvent()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsComposable(
                        modifier = Modifier.padding(innerPadding),
                        news = viewModel.news.value
                    )
                }
            }
        }
    }
}

@Composable
fun NewsComposable(modifier: Modifier = Modifier, news: News?) {
    LazyColumn(
        modifier = modifier,
    ) {
        /**
         * Loading Metadata
         */
        if (news != null) {
            item {
                Text(
                    text = "Metadata",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                ImageLoader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    url = news.image
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "title : ${news.title}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "date : ${news.date}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "type : ${news.type}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "Author",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Row {
                    ImageLoader(
                        url = news.authorImage,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp),
                        contentScale = ContentScale.Crop,
                        builder = {
                            CircleCropTransformation()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "author : ${news.author}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "authorWords : ${news.authorWords}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "News Details",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            /**
             * Loading news details using html tags Instance
             */
            items(news.structure) { tag ->
                when (tag) {
                    is H1 -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    is H2 -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    is H3 -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    is H4 -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    is H5 -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    is P -> {
                        Text(
                            text = tag.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    is Iframe -> {
                        WebViewComposable(url = tag.url)
                    }
                    is Image -> {
                        ImageLoader(
                            url = tag.src,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    is Ul -> {
                        tag.items.forEach { text ->
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    is Video -> {
                        Text(
                            text = "url of video : ${tag.src}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

/**
 * WebViewComposable to load Iframes objects
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComposable(modifier: Modifier = Modifier, url: String) {
    AndroidView(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth(),
        factory = { context ->

            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.domStorageEnabled = true
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SomosKudasaiTheme {
        NewsComposable(
            news = null
        )
    }
}