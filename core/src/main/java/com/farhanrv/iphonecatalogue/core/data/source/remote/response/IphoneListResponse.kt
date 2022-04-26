package com.farhanrv.iphonecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class IphoneListResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("image")
    val image: String
)