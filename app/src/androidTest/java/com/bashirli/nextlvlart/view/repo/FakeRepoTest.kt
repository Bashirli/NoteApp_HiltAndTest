package com.bashirli.nextlvlart.view.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bashirli.nextlvlart.view.model.Art
import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.repo.ArtInterface
import com.bashirli.nextlvlart.view.util.Resource

class FakeRepoTest : ArtInterface {
    private var arts= mutableListOf<Art>()
    private var artList=MutableLiveData<List<Art>>(arts)
    override suspend fun insertArt(art: Art) {
    arts.add(art)
    refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<Art>> {
    return artList
    }

    override suspend fun searchImage(string: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0,0, listOf()))

    }

    fun refreshData(){
        artList.postValue(arts)
    }


}