package com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.CreditSelectionView;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@InjectViewState
public class CreditFragmentPresenter extends MvpPresenter<CreditSelectionView> {
    private static final String TAG = "CreditFragmentPresenter";
    public MutableLiveData<String[]> termRange;
    public MutableLiveData<String[]> creditSumRange;
    public MutableLiveData<String> termCountValue;
    public MutableLiveData<String> creditCashValue;
    private String[] cashRange;

    public CreditFragmentPresenter() {
        initPresenter();
    }

    private void initPresenter() {
        MainActivityNavigateController.getInstance().hideBackButton();
        MainActivityNavigateController.getInstance().showBottomNavigationMenu();

        termRange = new MutableLiveData<>();
        termRange.setValue(getDatesRange());
        creditSumRange = new MutableLiveData<>();
        cashRange = getCashRange(); // set local variable
        creditSumRange.setValue(cashRange);

        termCountValue = new MutableLiveData<>();
        setTermCountValueText(1);

        creditCashValue = new MutableLiveData<>();
        setCreditCashValue(0);

        getViewState().updateUI();
    }

    private void setCreditCashValue(int i) {
        if(i < cashRange.length) {
            String s = cashRange[i];
            creditCashValue.setValue(s);
        }

        Log.i(TAG, "setCreditCashValue: " + i);
    }

    //
    private void setTermCountValueText(int i) {
        String s = "Термін (" + i + ")";
        termCountValue.setValue(s);
    }

    private String[] getCashRange() {
        int minValue = App.getInstance().getResources().getInteger(R.integer.calculate_cash_range_min_value);
        int maxValue = App.getInstance().getResources().getInteger(R.integer.calculate_cash_range_max_value);
        int iter = App.getInstance().getResources().getInteger(R.integer.calculate_cash_range_iter_value);

        int count = (maxValue - minValue)/iter + 1;
        String cashRange[] = new String[count];

        int currentValue = minValue;
        for (int i = 0; i < count; i++) {
            cashRange[i] = String.valueOf(currentValue);
            currentValue = currentValue + iter;
        }

        return cashRange;
    }

    private String[] getDatesRange() {
        List<Date> dates = getDatesFromNowCount(App.getInstance().getResources().getInteger(R.integer.max_count_of_credit_days));
        List<String> listDays = formatDaysToStingValue(dates);
        String s[] = new String[listDays.size()];
        try {
            s = listDays.toArray(s);
        } catch (ClassCastException e){
            Log.e(TAG, "getDatesRange: ", e);
        }

        return s;
    }

    public static List<String> formatDaysToStingValue(List<Date> dates) {
        List<String> list = new ArrayList<>();
        String week[] = App.getInstance().getResources().getStringArray(R.array.days_of_week);
        String monthes[] = App.getInstance().getResources().getStringArray(R.array.month_names);

        for (Date date : dates){
            StringBuilder sb = new StringBuilder();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
            sb.append(day < 10 ? "0" + day : String.valueOf(day));
            sb.append(" ");
            sb.append(monthes[month]);
            sb.append(" (");
            sb.append(week[day_of_week - 1]);
            sb.append(")");
            list.add(sb.toString());
        }

        return list;
    }


    public static List<Date> getDatesFromNowCount(int count){
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Calendar.getInstance().getTime());

        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DATE, 1);
            Date result = calendar.getTime();
            dates.add(result);
        }

        return dates;
    }

    // method calls when TermNumberPicker scrolled
    public void newRangeOfCreditSelected(int countDaysOfCredit) {
        setTermCountValueText(countDaysOfCredit);
    }

    public void newCashValueSelected(int newVal) {
        setCreditCashValue(newVal);
    }
}
