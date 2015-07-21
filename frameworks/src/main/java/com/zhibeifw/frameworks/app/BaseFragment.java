package com.zhibeifw.frameworks.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.andreabaccega.widget.FormEditText;
import com.zhibeifw.frameworks.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/6/1 0001.
 */
public class BaseFragment extends Fragment {

    public static final String ARG_FRAGMENT_TITLE = "arg_fragment_title";

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

    private CharSequence mTitle = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_FRAGMENT_TITLE);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_FRAGMENT_TITLE);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.submit);
        item.setTitle(getSubmitItemTitle());
    }

    public CharSequence getSubmitItemTitle() {
        return "";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (i == R.id.submit) {
            onSubmitItemSelected(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSubmitItemSelected(MenuItem item) {
    }

    public void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }

    public CharSequence getTitle() {
        return mTitle;
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
