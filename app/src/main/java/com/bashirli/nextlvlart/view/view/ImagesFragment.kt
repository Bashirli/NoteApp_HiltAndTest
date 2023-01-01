package com.bashirli.nextlvlart.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.databinding.FragmentImagesBinding
import com.bashirli.nextlvlart.view.adapter.ImageAdapter
import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.mvvm.ArtMVVM
import com.bashirli.nextlvlart.view.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImagesFragment @Inject constructor(
     val imageAdapter: ImageAdapter

): Fragment() {

     lateinit var viewModel:ArtMVVM
     lateinit var binding:FragmentImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity()).get(ArtMVVM::class.java)
        binding= FragmentImagesBinding.bind(view)
        subsToObserve()

        var job:Job?=null

        binding.editTextTextPersonName3.addTextChangedListener {
            job?.cancel()
            job=lifecycleScope.launch{
                delay(1000)
                it?.let {
                    if(it.toString().isNotEmpty()){
                        viewModel.searchImage(it.toString())
                    }
                }
            }
        }

        binding.imageRecycler.adapter=imageAdapter
        binding.imageRecycler.layoutManager=GridLayoutManager(requireContext(),3)
        imageAdapter.setOnItemClick {
            viewModel.setSelectedImage(it)
            findNavController().popBackStack()
        }
    }

    fun subsToObserve(){
        viewModel.imageList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{
                val urls=it.data?.hits?.map { ImageResponse-> ImageResponse.previewURL}
                imageAdapter.images=urls?: listOf()
                    binding.progressBar.visibility=View.GONE

                }
                Status.ERROR->{
                    binding.progressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.progressBar.visibility=View.VISIBLE
                }
            }
        }
    }

}