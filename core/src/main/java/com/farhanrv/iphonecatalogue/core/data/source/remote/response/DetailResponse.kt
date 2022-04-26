package com.farhanrv.iphonecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("release")
    val release: String,

    @field:SerializedName("dimension")
    val dimension: String,

    @field:SerializedName("storage")
    val storage: String,

    @field:SerializedName("ram")
    val ram: String,

    @field:SerializedName("display")
    val display: String,

    @field:SerializedName("battery")
    val battery: String,

    @field:SerializedName("selfie_camera")
    val selfieCamera: String,

    @field:SerializedName("main_camera")
    val mainCamera: String,

    @field:SerializedName("os")
    val os: String,

    @field:SerializedName("chipset")
    val chipset: String,

    @field:SerializedName("cpu")
    val cpu: String,

    @field:SerializedName("gpu")
    val gpu: String,

    @field:SerializedName("image")
    val image: String
)