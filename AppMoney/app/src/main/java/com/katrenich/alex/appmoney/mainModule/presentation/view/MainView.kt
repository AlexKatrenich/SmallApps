package com.katrenich.alex.appmoney.mainModule.presentation.view

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView

interface MainView : MvpView{
    fun showInfoDialog()
    fun bindFragment(mFragment: Fragment)
}