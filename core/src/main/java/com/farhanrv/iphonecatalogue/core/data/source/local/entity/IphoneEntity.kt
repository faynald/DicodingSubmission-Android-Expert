package com.farhanrv.iphonecatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iphone_entity")
data class IphoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val slug: String,

    val image: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)