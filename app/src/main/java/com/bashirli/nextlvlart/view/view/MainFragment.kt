package com.bashirli.nextlvlart.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.databinding.FragmentMainBinding
import com.bashirli.nextlvlart.view.adapter.ArtAdapter
import com.bashirli.nextlvlart.view.mvvm.ArtMVVM
import javax.inject.Inject

class MainFragment @Inject constructor(
    private val artAdapter: ArtAdapter
) : Fragment() {
 private lateinit var binding: FragmentMainBinding
 private lateinit var  viewModel:ArtMVVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private val swipeCallBack=object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewModel.deleteArt(artAdapter.arts.get(viewHolder.layoutPosition))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentMainBinding.bind(view)
        viewModel=ViewModelProvider(requireActivity()).get(ArtMVVM::class.java)

        subToObservers()
        binding.mainRecycler.adapter=artAdapter
        binding.mainRecycler.layoutManager=LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.mainRecycler)

        binding.fab.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToArtFragment())
        }

    }

    private fun subToObservers(){
        viewModel.artList.observe(viewLifecycleOwner){
        artAdapter.arts=it
        }
    }





}