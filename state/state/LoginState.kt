package com.general.view.state.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.general.R
import com.general.ext.*
import com.general.view.state.IState
import com.general.view.state.StateContainer

class LoginState : IState {

    private var view: View? = null
    private var tvTips: TextView? = null
    private var ivImg: ImageView? = null

    override fun onCreateView(
        context: Context,
        inflater: LayoutInflater,
        container: StateContainer
    ): View {
        return inflater.inflate(R.layout.base_state_login, container, false)
    }

    override fun onViewCreate(view: View) {
        this.view = view
        ivImg = view.findViewById(R.id.iv_img)
        tvTips = view.findViewById(R.id.tv_tips)
        view.findViewById<View>(R.id.btn_login)?.onClick {
            it.context.toActivity()?.toLogin()
        }
    }

    override fun hide() {
        view?.gone()
    }

    override fun show() {
        view?.visible()
    }

    fun setTips(@StringRes tips: Int) {
        tvTips?.setText(tips)
    }

    fun setImage(@DrawableRes img: Int) {
        ivImg?.setImageResource(img)
    }

    fun setBtnText(@StringRes tips: Int) {
        tvTips?.setText(tips)
    }
}