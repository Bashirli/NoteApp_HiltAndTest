package com.bashirli.nextlvlart.view.repo

import android.media.Image
import androidx.lifecycle.LiveData
import com.bashirli.nextlvlart.view.model.Art
import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.util.Resource

interface ArtInterface {
    suspend fun insertArt(art: Art)
    suspend fun deleteArt(art: Art)
     fun getArt():LiveData<List<Art>>

    suspend fun searchImage(string: String):Resource<ImageResponse>
}