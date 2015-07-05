package com.zhibeifw.frameworks.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.andreabaccega.widget.FormEditText;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/6/1 0001.
 */
public class BaseFragment extends Fragment {

    private BaseActivity mActivity;

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalStateException(getClass().getSimpleName() + " must be attached to a BaseActivity.");
        }
        mActivity = (BaseActivity) activity;

        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    public Drawable getDrawable(int id) throws Resources.NotFoundException {
        return getResources().getDrawable(id);
    }

    public void startFragmentActivity(Class<? extends Fragment> fragmentClass) {
        startFragmentActivity(fragmentClass, null);
    }

    public void startFragmentActivity(Class<? extends Fragment> fragmentClass, Bundle args) {
        Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
        intent.putExtra(Fragment.class.getSimpleName(), fragmentClass.getName());
        if (args != null) {
            intent.putExtras(args);
        }
        startActivity(intent);
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
}
