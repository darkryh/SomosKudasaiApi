package com.ead.lib.somoskudasai.models

data class NewsPreview(
    val title: String,
    val image: String,
    val type : String?,
    val date : String,
    val author : String?,
    val url : String
)