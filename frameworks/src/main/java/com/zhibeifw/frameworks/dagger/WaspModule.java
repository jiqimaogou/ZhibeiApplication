package com.zhibeifw.frameworks.dagger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountsException;
import android.app.Application;

import com.google.gson.Gson;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.parsers.GsonParser;
import com.orhanobut.wasp.utils.RequestInterceptor;
import com.orhanobut.wasp.utils.SimpleInterceptor;
import com.zhibeifw.frameworks.accounts.AccountUtils;
import com.zhibeifw.frameworks.accounts.AppAccount;
import com.zhibeifw.frameworks.app.TheActivityManager;

import java.io.IOException;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/6/24 0024.
 */
@Module
public class WaspModule {

    @Provides
    @Named("account")
    Wasp provideAccountWasp(final Application application, Gson gson) {
        RequestInterceptor interceptor = new SimpleInterceptor() {
            @Override
            public void onHeadersAdded(Map headers) {
                headers.put("Accept", "application/json");
                try {
                    AccountManager accountManager = AccountManager.get(application);
                    Account account = AccountUtils.getAccount(accountManager,
                                                              TheActivityManager.getInstance().getCurrentActivity());
                    AppAccount appAccount = new AppAccount(account, accountManager);
                    String password = appAccount.getPassword();
                    headers.put("TOKEN", password);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (AccountsException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return new Wasp.Builder(application).setEndpoint("http://app.shengwu315.com")
                                            .setParser(new GsonParser(gson))
                                            .setRequestInterceptor(interceptor)
                                            .build();
    }

    @Provides
    @Singleton
    Wasp provideWasp(Application application, Gson gson) {
        return new Wasp.Builder(application).setEndpoint("http://app.shengwu315.com")
                                            .setParser(new GsonParser(gson))
                                            .build();
    }
}
