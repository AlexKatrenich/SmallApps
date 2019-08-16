package com.katrenich.alex.appmoney.mainModule.presentation.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.katrenich.alex.appmoney.R
import com.katrenich.alex.appmoney.mainModule.presentation.presenter.MainActivityPresenter
import com.katrenich.alex.appmoney.mainModule.presentation.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainActivityPresenter

    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI(savedInstanceState)
    }

    private fun initUI(savedInstanceState: Bundle?) {
        // setting toolbar
        mToolbar = findViewById(R.id.tb_main_activity)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)


    }

    override fun showInfoDialog() {
        //TODO To change body of created functions use File | Settings | File Templates.
    }

    override fun bindFragment(mFragment: Fragment) {
        //TODO To change body of created functions use File | Settings | File Templates.
    }
}
