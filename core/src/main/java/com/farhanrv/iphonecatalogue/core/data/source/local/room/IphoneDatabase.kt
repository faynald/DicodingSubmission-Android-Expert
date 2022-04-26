package com.farhanrv.iphonecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farhanrv.iphonecatalogue.core.data.source.local.entity.DetailEntity
import com.farhanrv.iphonecatalogue.core.data.source.local.entity.IphoneEntity

@Database(entities = [IphoneEntity::class, DetailEntity::class], version = 1, exportSchema = false)
abstract class IphoneDatabase : RoomDatabase(){
    abstract fun iphoneDao(): IphoneDao
}