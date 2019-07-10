package com.katrenich.alex.klara.vacancyScreen.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.view.View;
import com.katrenich.alex.klara.vacancyScreen.model.VacancyResponse;


public class VacancyResponseViewModel {
    public ObservableField<VacancyResponse> mResponseObservableField;
    public MutableLiveData<VacancyResponse> mResponse;
    public void init() {
        mResponseObservableField = new ObservableField<>(new VacancyResponse());
        mResponse = new MutableLiveData<>();
    }

    public void sendBtnClick(View v){
        VacancyResponse vacancyResponse = mResponseObservableField.get();
        mResponse.setValue(vacancyResponse);
    }

}
