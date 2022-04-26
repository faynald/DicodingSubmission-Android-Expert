package com.farhanrv.iphonecatalogue.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone

class IphoneDiffCallback(private val mOldList: List<Iphone>, private val mNewList: List<Iphone>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldList.size

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldList[oldItemPosition].slug == mNewList[newItemPosition].slug

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldList[oldItemPosition] == mNewList[newItemPosition]

}