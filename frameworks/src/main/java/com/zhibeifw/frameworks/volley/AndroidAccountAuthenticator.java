package com.zhibeifw.frameworks.volley;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountsException;
import android.app.Activity;
import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.Authenticator;
import com.zhibeifw.frameworks.accounts.AccountUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2015/6/26 0026.
 */
public class AndroidAccountAuthenticator implements Authenticator {

    private final WeakReference<Activity> mActivity;

    private String mAuthTokenType;
    private AndroidAuthenticator mAuthenticator;

    public AndroidAccountAuthenticator(Activity activity, String authTokenType) {
        mAuthTokenType = authTokenType;
        mActivity = new WeakReference<Activity>(activity);
    }

    @Override
    public String getAuthToken() throws AuthFailureError {
        String authToken = getAuthenticator().getAuthToken();
        return authToken;
    }

    @Override
    public void invalidateAuthToken(String authToken) {
        try {
            getAuthenticator().invalidateAuthToken(authToken);
        } catch (AuthFailureError authFailureError) {
            throw new RuntimeException(authFailureError);
        }
    }

    @NonNull
    public Authenticator getAuthenticator() throws AuthFailureError {
        if (mAuthenticator != null)
            return mAuthenticator;

        final Activity activity = mActivity.get();
        if (activity != null) {
            Account account = null;
            try {
                account = AccountUtils.getAccount(AccountManager.get(activity), activity);
            } catch (IOException e) {
                throw new AuthFailureError(e.getMessage(), e);
            } catch (AccountsException e) {
                throw new AuthFailureError(e.getMessage(), e);
            }
            mAuthenticator = new AndroidAuthenticator(activity, account, mAuthTokenType);
            return mAuthenticator;
        } else {
            throw new IllegalStateException("activity is already released");
        }
    }
}