package com.bashirli.nextlvlart.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.nextlvlart.databinding.RecyclerimageBinding
import com.bashirli.nextlvlart.view.model.Art
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide:RequestManager
) : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {
    class ImageHolder(var binding:RecyclerimageBinding) :RecyclerView.ViewHolder(binding.root)

    var diffUtil=object: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

    }
    val recyclerListDiff= AsyncListDiffer(this,diffUtil)

    var images:List<String>
        get()=recyclerListDiff.currentList
        set(value) = recyclerListDiff.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
       val binding=RecyclerimageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    private var onItemClickListener : ((String)->Unit)? =null

    fun setOnItemClick(listener:(String)->Unit){
        onItemClickListener=listener
    }


    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        glide.load(images.get(position)).into(holder.binding.imageView3)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(images.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}