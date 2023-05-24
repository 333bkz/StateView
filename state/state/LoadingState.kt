package com.general.view.state.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.general.R
import com.general.ext.gone
import com.general.ext.visible
import com.general.view.state.IState
import com.general.view.state.StateContainer

class LoadingState : IState {

    private var view: View? = null

    override fun onCreateView(
        context: Context,
        inflater: LayoutInflater,
        container: StateContainer
    ): View {
        return inflater.inflate(R.layout.base_state_loading, container, false)
    }

    override fun onViewCreate(view: View) {
        this.view = view
    }

    override fun hide() {
        view?.gone()
    }

    override fun show() {
        view?.visible()
    }
}