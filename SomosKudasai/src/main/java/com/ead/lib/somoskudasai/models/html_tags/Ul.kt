package com.ead.lib.somoskudasai.models.html_tags

import com.ead.lib.somoskudasai.models.Tag

class Ul(value :  Any) : Tag(value) {
    @Suppress("UNCHECKED_CAST")
    val items : List<String> = value as List<String>
}