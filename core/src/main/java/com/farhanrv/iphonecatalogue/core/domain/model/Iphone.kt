package com.farhanrv.iphonecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Iphone(
    val name: String,
    val slug: String,
    val image: String,
    val isFavorite: Boolean
) : Parcelable