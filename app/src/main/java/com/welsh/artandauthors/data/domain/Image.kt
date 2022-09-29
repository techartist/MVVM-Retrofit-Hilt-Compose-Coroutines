package com.welsh.artandauthors.data.domain

import com.google.gson.annotations.SerializedName

data class Image(
    var id: String? = null,
    var author: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var url: String? = null,
    @SerializedName("download_url" )
    var downloadUrl : String? = null
)
