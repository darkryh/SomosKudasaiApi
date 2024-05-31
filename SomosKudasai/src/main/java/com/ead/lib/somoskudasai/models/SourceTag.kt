package com.ead.lib.somoskudasai.models

open class SourceTag(value : Any) : Tag(value) {
    val src : String = value.toString()
}