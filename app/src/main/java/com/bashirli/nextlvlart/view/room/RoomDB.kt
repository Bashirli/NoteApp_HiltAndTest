package com.bashirli.nextlvlart.view.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bashirli.nextlvlart.view.model.Art


@Database(entities = [Art::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao():RoomDAO
}