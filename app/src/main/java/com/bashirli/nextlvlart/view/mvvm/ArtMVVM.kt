package com.bashirli.nextlvlart.view.mvvm

import android.media.Image
import android.provider.MediaStore.Images
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.nextlvlart.view.model.Art
import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.repo.ArtInterface
import com.bashirli.nextlvlart.view.repo.ArtRepo
import com.bashirli.nextlvlart.view.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ArtMVVM @Inject constructor(
    private val  repo: ArtInterface
) : ViewModel() {


    //mainfragment
    val artList=repo.getArt()


    //imagesfragment
    private val images=MutableLiveData<Resource<ImageResponse>>()
     val imageList:LiveData<Resource<ImageResponse>>
    get() = images

    private val selectedImage=MutableLiveData<String>()
    val selectedImageURL:LiveData<String>
        get() {
            return selectedImage
        }

    //artfragment

    private var insertMessage=MutableLiveData<Resource<Art>>()
    val insertMSG : LiveData<Resource<Art>>
    get()=insertMessage

    fun resetMessage(){
        insertMessage=MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(string: String){
        selectedImage.postValue(string)
    }

    fun deleteArt(art:Art)=viewModelScope.launch {
        repo.deleteArt(art)
    }

    fun insertArt(art:Art)=viewModelScope.launch {
        repo.insertArt(art)
    }

    fun searchImage(string: String){
        if(string.isEmpty()){
            return
        }

        images.value=Resource.loading(null)

        viewModelScope.launch {
            val response=repo.searchImage(string)
            images.value=response
        }

    }


    fun makeArt(name:String,surname:String){
        if(name.isEmpty()||surname.isEmpty()){
            insertMessage.value=Resource.error("Empty",null)
            return
        }

        var art=Art(name,surname,selectedImage.value?:"")
        insertArt(art)
        setSelectedImage("")

    }

}