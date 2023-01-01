package com.bashirli.nextlvlart.view.repo

import androidx.lifecycle.LiveData
import com.bashirli.nextlvlart.view.model.Art
import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.retrofit.RetrofitAPI
import com.bashirli.nextlvlart.view.room.RoomDAO
import com.bashirli.nextlvlart.view.util.Resource
import javax.inject.Inject

class ArtRepo @Inject constructor(
   private var roomDAO: RoomDAO,
   private var  retrofitAPI: RetrofitAPI
) : ArtInterface {
    override suspend fun insertArt(art: Art) {
        roomDAO.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        roomDAO.deleteArt(art)
    }

    override  fun getArt(): LiveData<List<Art>> {
    return roomDAO.observeArts()
    }

    override suspend fun searchImage(string: String): Resource<ImageResponse> {
        return try {
        val response=retrofitAPI.imageSearch(string)
        if(response.isSuccessful){
            response.body()?.let {
                return@let Resource.success(it)
            }?: Resource.error("Error",null)
        }else{
            Resource.error("Error",null)
        }

        }catch (e:Exception){
            Resource.error("Error",null)
        }
    }
}