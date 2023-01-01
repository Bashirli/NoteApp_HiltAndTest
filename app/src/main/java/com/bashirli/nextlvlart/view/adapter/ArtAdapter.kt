package com.bashirli.nextlvlart.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.nextlvlart.databinding.RecyclerpostBinding
import com.bashirli.nextlvlart.view.model.Art
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtAdapter @Inject constructor(
    private val glide:RequestManager
): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    class ArtHolder(var binding:RecyclerpostBinding): RecyclerView.ViewHolder(binding.root) {

    }

   var diffUtil=object: DiffUtil.ItemCallback<Art>(){
       override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
           return oldItem==newItem
       }

       override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
           return oldItem==newItem
       }

   }
    val recyclerListDiff=AsyncListDiffer(this,diffUtil)

    var arts:List<Art>
    get()=recyclerListDiff.currentList
    set(value) = recyclerListDiff.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
    var binding=RecyclerpostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
    glide.load(arts.get(position).imageUrl).into(holder.binding.imageView2)
        holder.binding.textView.setText(arts.get(position).name)
        holder.binding.textView2.setText(arts.get(position).surname)
    }

    override fun getItemCount(): Int {
    return arts.size
    }
}