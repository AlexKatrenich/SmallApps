package com.katrenich.alex.klara.vacancyScreen.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.net.NetworkService;
import com.katrenich.alex.klara.utils.KlaraWebSiteHtmlParser;
import com.katrenich.alex.klara.vacancyScreen.adapter.VacancyListAdapter;
import com.katrenich.alex.klara.vacancyScreen.model.Vacancy;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VacancyListViewModel extends AndroidViewModel {
    public ObservableInt loading;
    private VacancyListAdapter mAdapter;
    private boolean dataWasLoad;
    public MutableLiveData<List<Vacancy>> vacancyList;
    public MutableLiveData<Vacancy> selectedVacancy;

    public VacancyListViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        loading = new ObservableInt(View.GONE);
        mAdapter = new VacancyListAdapter(R.layout.item_list_vacancies, this);
        vacancyList = new MutableLiveData<>();
        dataWasLoad = false;
        selectedVacancy = new MutableLiveData<>();
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
        NetworkService.getInstance()
                .getKlaraWebSiteInfo()
                .getVacancyCatalog()
                .subscribeOn(Schedulers.io())
                .map(KlaraWebSiteHtmlParser::parseListVacancies)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vacancies -> {
                    dataWasLoad = true;
                    vacancyList.setValue(vacancies);
                }, Throwable::printStackTrace);
    }

    public void setVacancyListInAdapter(List<Vacancy> vacancies){
        this.mAdapter.setVacancies(vacancies);
        this.mAdapter.notifyDataSetChanged();
    }


    public Vacancy getVacancyAt(Integer position){
        if (vacancyList != null && vacancyList.getValue().size() > position){
            return vacancyList.getValue().get(position);
        }

        return null;
    }

    public void onItemClick(Integer index){
        Vacancy vacancy = vacancyList.getValue().get(index);
        selectedVacancy.setValue(vacancy);
    }

}
