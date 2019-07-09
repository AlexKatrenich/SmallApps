package com.katrenich.alex.klara.vacancyScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.katrenich.alex.klara.App;
import com.katrenich.alex.klara.Mock;
import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.vacancyScreen.adapter.VacancyListAdapter;
import com.katrenich.alex.klara.vacancyScreen.model.Vacancy;

import java.util.List;

public class VacancyListViewModel extends AndroidViewModel {
    public static final String TAG = "VacancyListViewModel";
    public ObservableInt loading;
    private VacancyListAdapter mAdapter;
    private boolean dataWasLoad;
    public MutableLiveData<List<Vacancy>> vacancyList;

    public VacancyListViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        loading = new ObservableInt(View.GONE);
        mAdapter = new VacancyListAdapter(R.layout.item_list_vacancies, this);
        vacancyList = new MutableLiveData<>();
        dataWasLoad = false;
    }

    public VacancyListAdapter getAdapter(){
        return mAdapter;
    }

    public void fetchList(){
        if(!dataWasLoad){
            loadData();
        }
    }

    private void loadData() {
        vacancyList.setValue(Mock.getVacancyList());
        Log.i(TAG, "loadData: " + vacancyList.getValue());
        dataWasLoad = true;
        //TODO load from web-site
    }

    public void setVacancyListInAdapter(List<Vacancy> vacancies){
        this.mAdapter.setVacancies(vacancies);
        this.mAdapter.notifyDataSetChanged();
    }


    public Vacancy getVacancyAt(Integer position){
        if (vacancyList != null && vacancyList.getValue().size() > position){
            Log.i(TAG, "getVacancyAt: " + vacancyList.getValue().get(position));
            return vacancyList.getValue().get(position);
        }

        return null;
    }

    public void onItemClick(Integer index){
        Vacancy vacancy = vacancyList.getValue().get(index);
        Toast.makeText(App.getInstance().getBaseContext(), "Vacancy " + vacancy.getCaption() + " selected", Toast.LENGTH_SHORT).show();
    }

}
