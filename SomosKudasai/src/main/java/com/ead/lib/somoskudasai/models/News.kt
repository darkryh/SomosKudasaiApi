package com.ead.lib.somoskudasai.models

data class News(
    val title: String,
    val image: String,
    val type : String,
    val date : String,
    val author : String,
    val authorImage : String,
    val authorWords : String,
    val structure : List<Tag>
)
