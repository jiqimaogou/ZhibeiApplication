package com.zhibeifw.frameworks.accounts;

import android.app.Activity;

import com.zhibeifw.frameworks.app.BaseFragment;

/**
 * Created by Administrator on 2015/6/24 0024.
 */
public class AccountAuthenticatorFragment extends BaseFragment {

    private AccountAuthenticatorActivity mActivity;

    public AccountAuthenticatorActivity getAccountAuthenticatorActivity() {
        return mActivity;
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
}
