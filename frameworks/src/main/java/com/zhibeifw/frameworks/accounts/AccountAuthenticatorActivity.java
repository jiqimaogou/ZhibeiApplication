package com.zhibeifw.frameworks.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.zhibeifw.frameworks.R;
import com.zhibeifw.frameworks.app.AccountAuthenticatorBaseActivity;

import static android.accounts.AccountManager.KEY_ACCOUNT_NAME;
import static android.accounts.AccountManager.KEY_ACCOUNT_TYPE;
import static android.accounts.AccountManager.KEY_AUTHTOKEN;
import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;
import static com.zhibeifw.frameworks.accounts.AccountConstants.ACCOUNT_TYPE;

/**
 * Activity to login
 */
public class AccountAuthenticatorActivity extends AccountAuthenticatorBaseActivity {

    /**
     * Auth token type parameter
     */
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    /**
     * Initial user name
     */
    public static final String PARAM_USERNAME = "username";

    private static final String PARAM_CONFIRMCREDENTIALS = "confirmCredentials";

    private static final String TAG = "AccountAuthenticatorActivity";

    private AccountManager accountManager;

    private String authTokenType;

    /**
     * If set we are just checking that the user knows their credentials; this
     * doesn't cause the user's password to be changed on the device.
     */
    private Boolean confirmCredentials = false;

    private String authToken;

    private String password;

    /**
     * Was the original caller asking for an entirely new account?
     */
    protected boolean requestNewAccount = false;

    private String username;

    @Override
    public String getFname() {
        if (requestNewAccount) {
            return getString(R.string.login_fragment_name);
        }
        return getString(R.string.confirm_credentials_fragment_name);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        username = intent.getStringExtra(PARAM_USERNAME);
        authTokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        requestNewAccount = username == null;
        confirmCredentials = intent.getBooleanExtra(PARAM_CONFIRMCREDENTIALS, false);

        super.onCreate(savedInstanceState);

        accountManager = AccountManager.get(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Finish task if valid account exists
        if (requestNewAccount) {
            Account existing = AccountUtils.getPasswordAccessibleAccount(this);
            if (existing != null && !TextUtils.isEmpty(existing.name)) {
                String password = AccountManager.get(this).getPassword(existing);
                if (!TextUtils.isEmpty(password))
                    finishLogin(existing.name, password);
            }
            return;
        }
    }

    /**
     * Called when response is received from the server for confirm credentials
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller.
     *
     * @param result
     */
    protected void finishConfirmCredentials(boolean result) {
        final Account account = new Account(getUsername(), ACCOUNT_TYPE);
        accountManager.setPassword(account, getPassword());

        final Intent intent = new Intent();
        intent.putExtra(KEY_BOOLEAN_RESULT, result);
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Called when response is received from the server for authentication
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller. Also sets
     * the authToken in AccountManager for this account.
     *
     * @param username
     * @param password
     */

    protected void finishLogin(final String username, final String password) {
        final Intent intent = new Intent();
        intent.putExtra(KEY_ACCOUNT_NAME, username);
        intent.putExtra(KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
        if (ACCOUNT_TYPE.equals(authTokenType))
            intent.putExtra(KEY_AUTHTOKEN, password);
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Called when the authentication process completes (see attemptLogin()).
     */
    public void onAuthenticationResult() {
        String username = getUsername();
        Account account = new Account(username, ACCOUNT_TYPE);
        String password = getPassword();
        if (requestNewAccount) {
            accountManager.addAccountExplicitly(account, password, null);
        } else
            accountManager.setPassword(account, password);

        accountManager.setAuthToken(account, ACCOUNT_TYPE, getAuthToken());

        if (!confirmCredentials)
            finishLogin(username, password);
        else
            finishConfirmCredentials(true);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
