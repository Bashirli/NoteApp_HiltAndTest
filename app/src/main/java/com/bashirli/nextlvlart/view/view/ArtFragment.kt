package com.bashirli.nextlvlart.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.databinding.FragmentArtBinding
import com.bashirli.nextlvlart.view.adapter.ArtAdapter
import com.bashirli.nextlvlart.view.mvvm.ArtMVVM
import com.bashirli.nextlvlart.view.util.Status
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragment @Inject constructor(
    private val glide:RequestManager
) : Fragment() {
    private lateinit var binding:FragmentArtBinding
     lateinit var viewModel:ArtMVVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_art, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentArtBinding.bind(view)
        viewModel=ViewModelProvider(requireActivity()).get(ArtMVVM::class.java)
        subToOb()
        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToImagesFragment())
        }

        binding.button.setOnClickListener {
            viewModel.makeArt(binding.editTextTextPersonName.text.toString(),binding.editTextTextPersonName2.text.toString())

        }


    }

    private fun subToOb(){
        viewModel.selectedImageURL.observe(viewLifecycleOwner){
            glide.load(it).into(binding.imageView)
        }

        viewModel.insertMSG.observe(viewLifecycleOwner){
            when(it.status){
                Status.ERROR->{
                   Toast.makeText(requireContext(),it.message?:"Error",Toast.LENGTH_SHORT).show()
                }

                    Status.SUCCESS->{
                        Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        viewModel.resetMessage()
                    }

                Status.LOADING->{

                }
            }
        }

    }


}