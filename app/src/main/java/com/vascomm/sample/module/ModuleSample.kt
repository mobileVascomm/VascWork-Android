package com.vascomm.sample.module

import android.content.Context
import com.vascomm.vascwork.architecture.core.Module
import com.vascomm.vascwork.architecture.core.ViewStateInterface

interface Sample{
    fun sample1()
    fun sample2()
    fun sample3()
}

class ModuleSample constructor(iView:ViewStateInterface,context: Context):Module(iView,context),Sample{
    companion object{
        const val TAG_SAMPLE1 = "sample_1"
        const val TAG_SAMPLE2 = "sample_2"
        const val TAG_SAMPLE3 = "sample_3"
    }

    val dummy = ArrayList<String>().apply {
        add("Testing 1");add("Testing 2");add("Testing 3");add("Testing 4");add("Testing 5")
        add("Testing 6");add("Testing 7");add("Testing 8");add("Testing 9");add("Testing 10")

    }

    val dummy2 = ArrayList<String>().apply {
        add("Testing 11");add("Testing 11");add("Testing 11");add("Testing 11")
        add("Testing 22");add("Testing 22");add("Testing 22");add("Testing 22")
    }

    override fun sample1() {
        event(TAG_SAMPLE1){
            it.success("00","sample data berhasil",dummy)
        }
    }

    override fun sample2() {
        event(TAG_SAMPLE2){
            it.failure("99","sample data gagal","")
        }
    }

    override fun sample3() {
        event(TAG_SAMPLE3){
            it.success(data = dummy2)
        }
    }

}