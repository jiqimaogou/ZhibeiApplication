package com.zhibeifw.frameworks.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AccountsException;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import static android.accounts.AccountManager.KEY_AUTHTOKEN;
import static com.zhibeifw.frameworks.accounts.AccountConstants.ACCOUNT_TYPE;

/**
 * GitHub account model
 */
public class AppAccount {

    private static final String TAG = "AppAccount";

    private final Account account;

    private final AccountManager manager;

    /**
     * Create account wrapper
     *
     * @param account
     * @param manager
     */
    public AppAccount(final Account account, final AccountManager manager) {
        this.account = account;
        this.manager = manager;
    }

    /**
     * Get username
     *
     * @return username
     */
    public String getUsername() {
        return account.name;
    }

    /**
     * Get password
     *
     * @return password
     */
    public String getPassword() {
        return manager.getPassword(account);
    }

    /**
     * Get auth token
     *
     * @return token
     */
    public String getAuthToken() {
        AccountManagerFuture<Bundle> future = manager.getAuthToken(account, ACCOUNT_TYPE, false, null, null);

        try {
            Bundle result = future.getResult();
            return result != null ? result.getString(KEY_AUTHTOKEN) : null;
        } catch (AccountsException e) {
            Log.e(TAG, "Auth token lookup failed", e);
            return null;
        } catch (IOException e) {
            Log.e(TAG, "Auth token lookup failed", e);
            return null;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + account.name + ']';
    }
}
