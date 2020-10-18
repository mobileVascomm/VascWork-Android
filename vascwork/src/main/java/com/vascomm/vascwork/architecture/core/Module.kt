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

open class Module {

    protected val scope = CoroutineScope(Job() + Dispatchers.Main)
    protected var isActive = true
    private var context: Context
    private lateinit  var viewState: ViewStateInterface

    constructor( context: Context){
        this.context = context
    }

    constructor(viewState: ViewStateInterface, context: Context){
        this.viewState = viewState
        this.context = context
    }

    private inner class ModuleLifecyclerObserver : LifecycleObserver {
        fun addObserver(lifecycle: Lifecycle) = lifecycle.addObserver(this)

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onStart() =
            log(msg = "Lifecycle Observer Start Working").also { isActive = true }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() = log(msg = "Lifecycle Observer Stop Working").also { isActive = false }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() = log(msg = "Lifecycle Observer Stop Working").also { isActive = false }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() = log(msg = "Lifecycle Observer Resume Working").also { isActive = true }
    }

    fun addLifecyclerObserver(lifecycle: Lifecycle) {
        ModuleLifecyclerObserver().addObserver(lifecycle)
    }

    fun event(tag: String, action: (NetworkState) -> Unit) {
        action.invoke(
            NetworkState(
                tag,
                viewState,
                scope,
                isActive
            )
        )
    }

    fun event(tag: String, viewState: ViewStateInterface, action: (NetworkState) -> Unit) {
        action.invoke(
            NetworkState(
                tag,
                viewState,
                scope,
                isActive
            )
        )
    }

}