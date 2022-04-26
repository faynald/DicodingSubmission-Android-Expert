package com.farhanrv.iphonecatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_entity")
data class DetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val slug: String,

    val release: String,

    val dimension: String,

    val storage: String,

    val ram: String,

    val display: String,

    val battery: String,

    @ColumnInfo(name = "selfie_camera")
    val selfieCamera: String,

    @ColumnInfo(name = "main_camera")
    val mainCamera: String,

    val os: String,

    val chipset: String,

    val cpu: String,

    val gpu: String,

    val image: String

)
