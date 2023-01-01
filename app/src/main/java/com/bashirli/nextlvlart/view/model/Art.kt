package com.bashirli.nextlvlart.view.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    var name:String,
    var surname:String,
    var imageUrl:String,
 @PrimaryKey(autoGenerate = true)
 var id:Int?=null
)