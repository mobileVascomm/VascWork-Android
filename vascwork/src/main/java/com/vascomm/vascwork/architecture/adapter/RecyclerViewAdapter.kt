package com.vascomm.vascwork.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

abstract class RecyclerViewAdapter<DataClass,ViewHolder: RecyclerView.ViewHolder>
constructor(private val mLayout:Int,private val mViewHolderClass:Class<ViewHolder>,
            private val mData:List<DataClass>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mLayout,parent,false)

        return try{
            val constructor = mViewHolderClass.getConstructor(View::class.java)
            constructor.newInstance(view)
        }catch (error:Exception){
            throw RuntimeException(error)
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        bindView(holder,getItem(position),position)

    override fun getItemCount(): Int  = mData.size

    private fun getItem(position: Int):DataClass = mData[position]

    abstract fun bindView(holder: ViewHolder,model:DataClass,position: Int)

}