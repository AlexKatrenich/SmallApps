package com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katrenich.alex.smartiwaycopy.App;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.UserDocumentsView;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.model.User;

@InjectViewState
public class UserDocumentsFragmentPresenter extends MvpPresenter<UserDocumentsView> {
    private String userBirthDay;
    public MutableLiveData<String> btnBirthDateTitle;
    private UserInfo mUserInfo;

    public UserDocumentsFragmentPresenter() {
        mUserInfo = App.getUserInfo();

        btnBirthDateTitle = new MutableLiveData<>();
        getViewState().updateUI();
    }

    public void onBtnDateClicked(View view) {
        getViewState().showBirthDatePickerDialog();
    }

    public void onBtnNextClicked(String ipn, String passport) {
        if (ipn != null && passport != null && userBirthDay != null){
            if(!ipn.isEmpty() && !passport.isEmpty() && !userBirthDay.isEmpty()){
                User currentUser = mUserInfo.getCurrentUser();
                currentUser.setBirthDate(userBirthDay);
                currentUser.setInnCode(ipn);
                currentUser.setPassportSN(passport);
                int age = UserInfo.calculateAgeFromBirthDay(userBirthDay);
                currentUser.setAge(age);
                StringBuilder sb = new StringBuilder();
                sb.append("first_name: " + currentUser.getFirstName());
                sb.append("\r\n last_name: " + currentUser.getLastName());
                sb.append("\r\n middle_name: " + currentUser.getMiddleName());
                sb.append("\r\n inn_code: " + currentUser.getInnCode());
                sb.append("\r\n passport_sn: " + currentUser.getPassportSN());
                sb.append("\r\n mobile_phone: " + currentUser.getMobilePhone());
                sb.append("\r\n credit_term: " + mUserInfo.getCreditTerm());
                sb.append("\r\n birth_date: " + currentUser.getBirthDate());
                sb.append("\r\n age: " + currentUser.getAge());

                mUserInfo.setCurrentUser(currentUser);
                Toast.makeText(App.getInstance(), sb.toString(), Toast.LENGTH_LONG).show();
                return;
            }
        }

        getViewState().showMessageDialog(App.getInstance().getString(R.string.user_documents_fragment_alert_dialog_text));
    }

    public void setUserBirthDay(int year, int month, int dayOfMonth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth));
        stringBuilder.append(".");
        stringBuilder.append(month < 10 ? "0" + month : String.valueOf(month));
        stringBuilder.append(".");
        stringBuilder.append(year);

        userBirthDay = stringBuilder.toString();
        btnBirthDateTitle.setValue("Дата народження: " + userBirthDay);
    }
}
