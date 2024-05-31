package com.ead.lib.somoskudasai.models.html_tags

import com.ead.lib.somoskudasai.models.Tag

class Iframe(value :  Any) : Tag(value) {
    val url : String = value.toString()
}