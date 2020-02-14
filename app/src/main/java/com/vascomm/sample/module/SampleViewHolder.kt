package com.vascomm.sample.module

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sample.view.*

class SampleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    fun onBind(data:String){
        itemView.text_sample.text = data
    }
}