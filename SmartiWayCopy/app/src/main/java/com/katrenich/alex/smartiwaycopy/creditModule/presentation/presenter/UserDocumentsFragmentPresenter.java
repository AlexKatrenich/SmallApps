package com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.UserDocumentsView;
import com.katrenich.alex.smartiwaycopy.creditModule.util.CreditController;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.mainModule.util.MainActivityNavigateController;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;
import com.katrenich.alex.smartiwaycopy.utils.ApiKeyPrefUtils;

import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class UserDocumentsFragmentPresenter extends MvpPresenter<UserDocumentsView> {
    private static final String TAG = "UserDocumentsFragmentP";
    private String userBirthDay;
    private String userIPN;
    private String userPassport;
    public MutableLiveData<String> btnBirthDateTitle;
    private UserInfo mUserInfo;
    public MutableLiveData<Integer> visibilityBtnNext;
    private Disposable dataLoad;
    private boolean navigateToCreditFragment;

    public UserDocumentsFragmentPresenter() {
        mUserInfo = App.getUserInfo();
        visibilityBtnNext = new MutableLiveData<>();
        btnBirthDateTitle = new MutableLiveData<>();
        navigateToCreditFragment = false;
        getViewState().updateUI();
    }

    public void onBtnDateClicked(View view) {
        getViewState().showBirthDatePickerDialog();
    }

    public void onBtnNextClicked(String ipn, String passport) {
        if (ipn != null && passport != null && userBirthDay != null){
            if(!ipn.isEmpty() && !passport.isEmpty() && !userBirthDay.isEmpty()){

                mUserInfo.getCurrentUser().setBirthDate(userBirthDay);
                mUserInfo.getCurrentUser().setInnCode(ipn);
                mUserInfo.getCurrentUser().setPassportSN(passport);
                int age = UserInfo.calculateAgeFromBirthDay(userBirthDay);
                mUserInfo.getCurrentUser().setAge(age);

                StringBuilder sb = new StringBuilder();
                sb.append("first_name: " + mUserInfo.getCurrentUser().getFirstName());
                sb.append("\r\n last_name: " + mUserInfo.getCurrentUser().getLastName());
                sb.append("\r\n middle_name: " + mUserInfo.getCurrentUser().getMiddleName());
                sb.append("\r\n inn_code: " + mUserInfo.getCurrentUser().getInnCode());
                sb.append("\r\n passport_sn: " + mUserInfo.getCurrentUser().getPassportSN());
                sb.append("\r\n mobile_phone: " + mUserInfo.getCurrentUser().getMobilePhone());
                sb.append("\r\n credit_term: " + mUserInfo.getCreditTerm());
                sb.append("\r\n birth_date: " + mUserInfo.getCurrentUser().getBirthDate());
                sb.append("\r\n age: " + mUserInfo.getCurrentUser().getAge());

                Log.i(TAG, "onBtnNextClicked: " + sb);

                String token = ApiKeyPrefUtils.getApiKey(App.getInstance());

                if(dataLoad != null && !dataLoad.isDisposed()) return;
                try {
                    MainActivityNavigateController.getInstance().showProgress();
                    dataLoad = CreditController.getInstance()
                            .setUserDataToServer(mUserInfo.getCurrentUser(), token)
                            .subscribe(addUserDataResponseResponse -> {
                                MainActivityNavigateController.getInstance().hideProgress();
                                if (addUserDataResponseResponse.isSuccessful()) {
                                    getCredit();
                                } else {
                                    if(addUserDataResponseResponse.code() == 422){
                                        getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_inn_not_valid_message));
                                    }
                                    Log.i(TAG, "onBtnNextClicked: RESPONSE_CODE= " + addUserDataResponseResponse.code());
                                }
                            }, throwable -> {
                                getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_internet_connection_problem_message));
                                MainActivityNavigateController.getInstance().hideProgress();
                            });
                } catch (DataFormatException e) {
                    Log.e(TAG, "onBtnNextClicked: ", e);
                    MainActivityNavigateController.getInstance().hideProgress();
                }

                return;
            }
        }

        getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_text));
    }

    private void getCredit() {
        MainActivityNavigateController.getInstance().showProgress();
        String token = ApiKeyPrefUtils.getApiKey(App.getInstance());
        dataLoad = CreditController.getInstance()
                .sendCreditQuery(token)
                .subscribe(baseResponseResponse -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    if(baseResponseResponse.isSuccessful()){
                        navigateToCreditFragment = true;
                        getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_success_data_set_message));
                    } else {
                        if(baseResponseResponse.code() == 406){
                            getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_credit_query_already_send_message));
                        }
                        getViewState().showMessageDialog(baseResponseResponse.message());
                    }
                }, throwable -> {
                    MainActivityNavigateController.getInstance().hideProgress();
                    Log.i(TAG, "getCredit: " + throwable.getMessage());
                });

    }

    @Override
    public void onDestroy() {
        if(dataLoad != null) dataLoad.dispose();
        super.onDestroy();
    }

    // method check if all data was entered
    private void checkFullDataEntering(){
        boolean b = false;
        if(userIPN != null && userIPN.length() == 10){
            b = true;
        } else {
            visibilityBtnNext.setValue(View.GONE);
            return;
        }

        if(userPassport != null && userPassport.length() == 8 && userBirthDay != null){
            b = true;
        } else {
            visibilityBtnNext.setValue(View.GONE);
            return;
        }

        if (b) visibilityBtnNext.setValue(View.VISIBLE);

    }

    public void setUserBirthDay(int year, int month, int dayOfMonth) {
        month++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth));
        stringBuilder.append(".");
        stringBuilder.append(month < 10 ? "0" + month : String.valueOf(month));
        stringBuilder.append(".");
        stringBuilder.append(year);

        userBirthDay = stringBuilder.toString();
        btnBirthDateTitle.setValue("Дата народження: " + userBirthDay);
        checkFullDataEntering();
    }


    public void setUserIPN(String ipn){
        userIPN = ipn;
        checkFullDataEntering();
    }

    public void setUserPassport(String passport) {
        userPassport = null;
        if(verifyPassport(passport)){
            userPassport = passport;
        }
        checkFullDataEntering();
    }

    private boolean verifyPassport(String passport){
        String pattern = "[а-яА-Я]{2}\\d{6}";
        return Pattern.compile(pattern).matcher(passport).matches();
    }

    public void onCancelBtnDialogClicked() {
        if(navigateToCreditFragment) MainActivityNavigateController.getInstance().navigate(R.id.creditFragment);
    }
}
