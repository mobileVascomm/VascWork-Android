package com.vascomm.vascwork.architecture.core

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.vascomm.vascwork.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class Module constructor(val viewState: ViewStateInterface, val context: Context){
    protected  val scope = CoroutineScope(Job()+Dispatchers.Main)

    private inner class ModuleLifecyclerObserver: LifecycleObserver{
        fun addObserver(lifecycle: Lifecycle) = lifecycle.addObserver(this)

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onStart() =
            log(msg = "Lifecycle Observer Start Working")

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() = scope.cancel("Module stop working").also {
            log(msg = "Lifecyler Observer Stop Working")
        }
    }

    fun addLifecyclerObserver(lifecycle:Lifecycle) {
        ModuleLifecyclerObserver().addObserver(lifecycle)
    }

    fun event(tag:String,action : (NetworkState) -> Unit){
        action.invoke(
            NetworkState(
                tag,
                viewState,
                scope
            )
        )
    }

}