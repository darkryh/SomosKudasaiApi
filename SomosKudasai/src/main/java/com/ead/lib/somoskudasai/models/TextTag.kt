package com.ead.lib.somoskudasai.models

open class TextTag(value : Any) : Tag(value) {
    val text : String = value.toString()
}