package com.farhanrv.iphonecatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.iphonecatalogue.core.R
import com.farhanrv.iphonecatalogue.core.databinding.ItemListIphoneBinding
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone

class IphoneAdapter : RecyclerView.Adapter<IphoneAdapter.ListViewHolder>() {

    private val listData = ArrayList<Iphone>()
    var onItemClick: ((Iphone) -> Unit)? = null

    fun setData(newListData: List<Iphone>?) {
        if (newListData == null) return

        val diffCallback = IphoneDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listData.clear()
        listData.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_iphone, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListIphoneBinding.bind(itemView)
        fun bind(data: Iphone) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .override(640, 237)
                    .into(imgThumbnail)
                tvItemTitle.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}