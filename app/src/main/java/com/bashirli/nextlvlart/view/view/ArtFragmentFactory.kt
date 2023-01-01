package com.bashirli.nextlvlart.view.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bashirli.nextlvlart.view.adapter.ArtAdapter
import com.bashirli.nextlvlart.view.adapter.ImageAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide:RequestManager,
    private val artAdapter: ArtAdapter,
    private val imageAdapter: ImageAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ArtFragment::class.java.name->ArtFragment(glide)
            MainFragment::class.java.name->MainFragment(artAdapter)
            ImagesFragment::class.java.name->ImagesFragment(imageAdapter)
            else->return super.instantiate(classLoader, className)

        }

        }
}