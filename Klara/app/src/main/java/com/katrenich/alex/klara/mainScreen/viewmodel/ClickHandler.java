package com.katrenich.alex.klara.mainScreen.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.assortmentScreen.ui.ProductsListActivity;
import com.katrenich.alex.klara.placesListScreen.ui.CoffeeShopsActivity;
import com.katrenich.alex.klara.vacancyScreen.ui.VacancyListActivity;

public class ClickHandler {

    private static final String TAG = "ClickHandler";

    public void onRangeButtonClicked(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, ProductsListActivity.class);
        context.startActivity(intent);
    }

    public void onPattiesButtonClicked(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, CoffeeShopsActivity.class);
        context.startActivity(intent);
    }

    public void onVacancyButtonClicked(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, VacancyListActivity.class);
        context.startActivity(intent);
    }

    public void onPhoneNumberClicked(View v){
        String phone = v.getContext().getResources().getString(R.string.main_phone_number);
        phone = phone.replaceAll(" ", "");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        v.getContext().startActivity(intent);
    }

    public void onEmailClicked(View v){
        String email = v.getContext().getResources().getString(R.string.main_email_value);
        email = email.trim();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        try {
            v.getContext().startActivity(intent);
        } catch (Exception e){
            Log.e(TAG, "onEmailClicked: ", e);
        }
    }


}
