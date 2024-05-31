package com.ead.lib.somoskudasai.models

data class Home(
    val trendsOfTheWeek : List<NewsPreview>,
    val recentNews : List<NewsPreview>,
    val japanNews : List<NewsPreview>,
    val otakuCulture : List<NewsPreview>,
    val reviews : List<NewsPreview>
)