package com.general.view.state

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

fun View.bindState(): StateContainer? =
    (parent as? ViewGroup)?.let {
        if (it is StateContainer) { //已绑定
            it
        } else {
            var index = 0
            val container = StateContainer(this)
            for (i in 0 until it.childCount) {
                if (it.getChildAt(i) == this) {
                    index = i
                    break
                }
            }
            it.removeView(this)
            it.addView(container, index, this.layoutParams)
            container.addView(this, 0, ViewGroup.LayoutParams(-1, -1))
            container
        }
    }

fun <T : IState> View.showState(clazz: Class<T>) = (parent as? StateContainer)?.show(clazz)

fun View.hideState() {
    (parent as? StateContainer)?.hide()
}

fun <T : IState> View.getState(clazz: Class<T>) = (parent as? StateContainer)?.get(clazz)

@SuppressLint("ViewConstructor")
class StateContainer constructor(
    val originView: View,
) : FrameLayout(originView.context, null, 0) {

    var state: IState? = null
        private set

    fun <T : IState> show(clazz: Class<T>): T? {
        originView.visibility = View.INVISIBLE
        val precState = state
        if (precState == null || precState.javaClass != clazz) {
            val state = clazz.newInstance()
            if (childCount > 1) { //替换状态
                removeViewAt(1)
            }
            val stateView = state.onCreateView(context, LayoutInflater.from(context), this)
            state.onViewCreate(stateView)
            addView(stateView)
            this.state = state
        }
        state?.show()
        @Suppress("UNCHECKED_CAST")
        return state as? T
    }

    fun hide() {
        originView.visibility = View.VISIBLE
        state?.hide()
    }

    fun <T : IState> get(clazz: Class<T>): T? {
        if (state?.javaClass == clazz) {
            @Suppress("UNCHECKED_CAST")
            return state as? T
        }
        return null
    }
}