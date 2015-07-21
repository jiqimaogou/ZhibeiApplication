package com.zhibeifw.frameworks.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.andreabaccega.widget.FormEditText;
import com.easemob.chatuidemo.activity.LoginFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/6/24 0024.
 */
public class AccountAuthenticatorFragment extends LoginFragment {

    private AccountAuthenticatorActivity mActivity;

    public AccountAuthenticatorActivity getAccountAuthenticatorActivity() {
        return mActivity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.inject(this, view);
    }

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof AccountAuthenticatorActivity)) {
            throw new IllegalStateException(getClass().getSimpleName() + " must be attached to a AccountAuthenticatorActivity.");
        }
        mActivity = (AccountAuthenticatorActivity) activity;

        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    protected void testFieldsValidity(Runnable runnable, FormEditText... allFields) {
        boolean allValid = true;
        for (FormEditText field : allFields) {
            allValid = field.testValidity() && allValid;
        }

        if (allValid) {
            // YAY
            runnable.run();
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
    }

    @Override
    public void startMainActivity() {
        getActivity().finish();
    }
}
