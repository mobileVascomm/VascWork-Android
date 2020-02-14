package com.vascomm.sample.module

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vascomm.sample.R
import com.vascomm.vascwork.architecture.adapter.RecyclerViewAdapter
import com.vascomm.vascwork.architecture.adapter.RecyclerViewPagination
import com.vascomm.vascwork.architecture.core.Result
import com.vascomm.vascwork.architecture.core.ViewStateInterface
import kotlinx.android.synthetic.main.activity_sample_module.*

class ActivitySampleModule:AppCompatActivity(),ViewStateInterface {
    private val sampleModule by lazy { ModuleSample(this,applicationContext) }
    private val lazyLoad by lazy { RecyclerViewPagination() }
    private val data = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_module)
        setSample()
    }

    private fun setSample(){
        list_sample.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,
            false)
        list_sample.adapter  = object : RecyclerViewAdapter<String,SampleViewHolder>(R.layout.item_sample,
            SampleViewHolder::class.java,data){
            override fun bindView(holder: SampleViewHolder, model: String, position: Int) {
                holder.onBind(model)
            }
        }
        lazyLoad.setPagination(list_sample,10){ sampleModule.sample3() }
        sampleModule.sample2()
    }

    override fun onSuccess(result: Result) {
        when(result.tag){
            ModuleSample.TAG_SAMPLE1 -> (result.data as ArrayList<*>).map { data.add(it as String) }
                .also { list_sample.adapter?.notifyDataSetChanged();lazyLoad.isLoad(true) }
            ModuleSample.TAG_SAMPLE3 -> (result.data as ArrayList<*>).map{data.add(it as String)}.
                also { list_sample.adapter?.notifyDataSetChanged();lazyLoad.isLoad(true) }
        }
    }

    override fun onFailure(result: Result) {
        Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(result: Result) {
    }
}