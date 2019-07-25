package com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.InfoView;

@InjectViewState
public class InfoDialogPresenter extends MvpPresenter<InfoView> {

    public void onPhoneBtnClick(View v){
        Context context = v.getContext();
        String phone = context.getString(R.string.info_dialog_fragment_phone_value);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        context.startActivity(intent);
    }

    public void onQuestionsBtnClick(View v){
        Context context = v.getContext();
        String webAddress = context.getString(R.string.info_dialog_fragment_website_address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webAddress));
        context.startActivity(intent);
    }

    public void onCancelBtnClick(View v){
        getViewState().hideDialog();
    }
}
