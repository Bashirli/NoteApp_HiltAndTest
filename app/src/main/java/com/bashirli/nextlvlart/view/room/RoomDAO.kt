package com.bashirli.nextlvlart.view.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bashirli.nextlvlart.view.model.Art
import retrofit2.Response

@Dao
interface RoomDAO {
@Insert
suspend fun insertArt(art:Art)

@Delete
suspend fun deleteArt(art: Art)

@Query("Select * from arts")
 fun observeArts():LiveData<List<Art>>

}