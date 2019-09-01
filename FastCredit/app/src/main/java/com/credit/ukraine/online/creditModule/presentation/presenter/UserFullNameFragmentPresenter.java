package com.credit.ukraine.online.creditModule.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.credit.ukraine.online.App;
import com.credit.ukraine.online.R;
import com.credit.ukraine.online.creditModule.presentation.view.UserFullNameView;
import com.credit.ukraine.online.creditModule.util.UserInfo;
import com.credit.ukraine.online.mainModule.util.MainActivityNavigateController;
import com.credit.ukraine.online.model.User;

@InjectViewState
public class UserFullNameFragmentPresenter extends MvpPresenter<UserFullNameView> {
    private UserInfo mUserInfo;

    public UserFullNameFragmentPresenter() {
        mUserInfo = App.getUserInfo();
    }

    public void onBtnNextClicked(String firstName, String middleName, String lastName) {
        if(firstName != null && middleName != null && lastName != null){
            if(!firstName.isEmpty() && !middleName.isEmpty() && !lastName.isEmpty()){
                User currentUser = mUserInfo.getCurrentUser();
                currentUser.setFirstName(firstName);
                currentUser.setMiddleName(middleName);
                currentUser.setLastName(lastName);
                mUserInfo.setCurrentUser(currentUser);
                MainActivityNavigateController.getInstance().navigate(R.id.action_userFullName_to_userDocuments);
                MainActivityNavigateController.getInstance().showBackButton();
                return;
            }
        }

        getViewState().showMessage(App.getInstance().getString(R.string.user_full_name_fragment_alert_dialog_text));
    }
}
