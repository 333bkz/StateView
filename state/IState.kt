package com.general.view.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View

interface IState {

    fun onCreateView(
        context: Context,
        inflater: LayoutInflater,
        container: StateContainer
    ): View

    fun onViewCreate(view: View)

    fun hide()

    fun show()
}