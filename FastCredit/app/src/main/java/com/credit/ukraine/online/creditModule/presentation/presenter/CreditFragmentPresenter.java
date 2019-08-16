package com.credit.ukraine.online.creditModule.presentation.presenter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.credit.ukraine.online.App;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.creditModule.presentation.view.CreditSelectionView;
import com.credit.ukraine.online.creditModule.util.CreditCalculator;
import com.credit.ukraine.online.creditModule.util.CreditController;
import com.credit.ukraine.online.creditModule.util.UserInfo;
import com.credit.ukraine.online.mainModule.util.MainActivityNavigateController;
import com.credit.ukraine.online.model.User;

import java.text.SimpleDateFormat;
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
    private UserInfo mUserInfo;

    private int currentCashNumberPickerValue;

    public CreditFragmentPresenter() {
        initPresenter();
    }

    // init all variables there
    private void initPresenter() {
        mUserInfo = App.getUserInfo();

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

    // method for setting new value in variable `creditCashValue`
    private void setCreditCashValue(int i) {
        if(i < cashRange.length) {
            String s = cashRange[i];
            creditCashValue.setValue(s);
            CreditController.getInstance().getCredit().setLoanAmount(Integer.valueOf(s));
        }

        Log.i(TAG, "setCreditCashValue: " + i);
    }

    // method for setting new value in variable `termCountValue`
    private void setTermCountValueText(int i) {
        String s = "Термін (" + i + ")";
        termCountValue.setValue(s);
        mUserInfo.setCreditTerm(i);

        /*Set credit return date*/
        Date retDate = getDateFromNow(i);
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String returnDate = sdt.format(retDate);
        CreditController.getInstance().getCredit().setReturnDate(returnDate);
    }

    // method for getting cash range from resources constants
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

    // method for getting days range from resources constants
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

    public static Date getDateFromNow(int count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DATE, count);

        return calendar.getTime();
    }

    // method calls when TermNumberPicker scrolled
    public void newRangeOfCreditSelected(int countDaysOfCredit) {
        setTermCountValueText(countDaysOfCredit);
    }

    // method calls when CashNumberPicker scrolled
    public void newCashValueSelected(int newVal) {
        currentCashNumberPickerValue = newVal;
        setCreditCashValue(newVal);
    }

    public void onBtnGetCreditClicked(View view) {
        User user = mUserInfo.getCurrentUser();
        if (user != null){
            String mobilePhone = user.getMobilePhone();
            Log.i(TAG, "onBtnGetCreditClicked: mobPhone = " + mobilePhone);
        } else {
            Log.i(TAG, "onBtnGetCreditClicked: User = null");
        }

        // TODO Calculate credit

        MainActivityNavigateController.getInstance().hideBottomNavigationMenu();
        MainActivityNavigateController.getInstance().showBackButton();
        MainActivityNavigateController.getInstance().navigate(R.id.action_credit_to_userFullName);
    }

    public void onBtnCreditInfoClicked(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(App.getInstance().getString(R.string.credit_fragment_info_dialog_credit) + " ");
        sb.append(creditCashValue.getValue());
        sb.append(".00 ");
        sb.append(App.getInstance().getString(R.string.cash_currency));
        sb.append("\r\n");
        sb.append(App.getInstance().getString(R.string.credit_fragment_info_dialog_percents) + " ");
        sb.append("0"); // TODO calculate percents and set to message
        sb.append(".00 ");
        sb.append(App.getInstance().getString(R.string.cash_currency));
        sb.append("\r\n");
        sb.append(App.getInstance().getString(R.string.credit_fragment_info_dialog_total) + " ");
        sb.append(creditCashValue.getValue()); // TODO calculate percents+credit value and set to message
        sb.append(".00 ");
        sb.append(App.getInstance().getString(R.string.cash_currency));

        getViewState().showCreditInfo(sb.toString());
    }

    // method calls when user click on button to set credit cash value manually
    public void onSetCreditValueClicked(View view) {
        getViewState().showSetCreditSumDialog();
    }

    // method calls when user click on button to set credit term manually
    public void onSetCreditTermClicked(View view) {
        getViewState().showSetCreditTermDialog();
    }

    // method call from UI, when user set value manually in dialog
    public void setNewTermCredit(String s) {
        try {
            int term = Integer.valueOf(s);
            int maxRange = App.getInstance().getResources().getInteger(R.integer.max_count_of_credit_days);
            if (term > 0 && term <= maxRange){
                setTermCountValueText(term);
                getViewState().scrollTermToValue(term);
            }
        } catch (NumberFormatException e){
            Log.e(TAG, "setNewTermCredit: ", e);
        }
    }

    // method call from UI, when user set value manually in dialog
    public void setNewCreditSum(String s) {
        try {
            int sum = Integer.valueOf(s);
            int maxSum = App.getInstance().getResources().getInteger(R.integer.calculate_cash_range_max_value);
            int minSum = App.getInstance().getResources().getInteger(R.integer.calculate_cash_range_min_value);
            if (sum > minSum && sum <= maxSum){
                // calculate new credit summary range for NumberPicker
                String[] sumNewRange = CreditCalculator.createNewSumRange(creditSumRange.getValue(), sum);
                creditSumRange.setValue(sumNewRange);
                // calculate new position of numberPicker
                int position = CreditCalculator.getCurrentElementPosition(sumNewRange, sum);
                getViewState().scrollCreditSumToValue(position);
                creditCashValue.setValue(String.valueOf(sum));
            }
        } catch (NumberFormatException e){
            Log.e(TAG, "setNewTermCredit: ", e);
        }
    }
}
