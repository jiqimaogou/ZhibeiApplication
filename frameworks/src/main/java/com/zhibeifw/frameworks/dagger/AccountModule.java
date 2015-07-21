package com.zhibeifw.frameworks.dagger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountsException;
import android.app.Application;

import com.yayandroid.theactivitymanager.TheActivityManager;
import com.zhibeifw.frameworks.accounts.AccountUtils;
import com.zhibeifw.frameworks.accounts.AppAccount;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/7/1 0001.
 */
@Module
public class AccountModule {

    @Provides
    AccountManager provideAccountManager(Application application) {
        return AccountManager.get(application);
    }

    @Provides
    Account provideAccount(AccountManager accountManager) {
        try {
            return AccountUtils.getAccount(accountManager, TheActivityManager.getInstance().getCurrentActivity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AccountsException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    AppAccount provideAppAccount(AccountManager accountManager, Account account) {
        return new AppAccount(account, accountManager);
    }
}
