package com.vascomm.vascwork.architecture.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vascomm.vascwork.log

class RecyclerViewPagination {
    private var isLoad = false
    private var offSet = 0

    fun setPagination(recyclerView: RecyclerView, offset:Int, unit:() -> Unit){
        offSet += offset
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy >= 0){
                    (recyclerView.layoutManager as LinearLayoutManager).apply {
                        val lastVisibleItem = childCount
                        val past = findFirstVisibleItemPosition()
                        log("lazy load","load")
                        log("last data",(lastVisibleItem+past).toString())
                        log("offset",offset.toString())
                        if(lastVisibleItem+past >= offSet && isLoad){
                            offSet += offset
                            isLoad(false)
                            unit.invoke()

                        }
                    }
                }
            }
        })
    }


    fun isLoad(status:Boolean){
        isLoad = status
    }
}