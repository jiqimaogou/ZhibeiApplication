package com.zhibeifw.frameworks.app;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhibeifw.frameworks.R;

public class WebViewFragment extends BaseFragment implements OnBackPressedListener {

    private static final String TAG = WebViewFragment.class.getSimpleName();

    public static final int OBTAIN_PROGRESS_EVERY = 100;
    public static final String ARG_URL = "url";

    private ProgressBar mProgressBar;
    private WebView mWebView;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            int progress = mWebView.getProgress();
            mProgressBar.setProgress(progress);
            if (progress != 100) {
                mHandler.postDelayed(this, OBTAIN_PROGRESS_EVERY);
            }
        }
    };

    public static WebViewFragment newInstance(String URL) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle args = new Bundle(1);
        args.putString(ARG_URL, URL);
        webViewFragment.setArguments(args);
        return webViewFragment;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.web_view, container, false);

        mWebView = (WebView) root.findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);

        mWebView.loadUrl(getArguments().getString(ARG_URL));

        // to open links in this webview, use this line of code:
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                mHandler.postDelayed(mRunnable, OBTAIN_PROGRESS_EVERY);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                if (getParentFragment() != null) {
                    if (mWebView.canGoBack()) {
                        getBaseActivity().setDisplayHomeAsUpEnabled(true);
                    } else {
                        getBaseActivity().setDisplayHomeAsUpEnabled(false);
                    }
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d(TAG, "onReceivedError: " + errorCode + " : " + description + " : " + failingUrl);

                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        // to enable JavaScript, use this line of code:
        mWebView.getSettings().setJavaScriptEnabled(true);
        // to load pages faster, use this line of code:
        mWebView.getSettings().setAppCacheEnabled(true);
        // to enable pinch to zoom, use this line of code:
        mWebView.getSettings().setBuiltInZoomControls(true);
        //        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseActivity().addOnBackPressedListener(this);
    }

    @Override
    public boolean onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        mHandler.removeCallbacks(mRunnable);
        getBaseActivity().removeOnBackPressedListener(this);
        getBaseActivity().setDisplayHomeAsUpEnabled(false);
        super.onDestroyView();
    }
}
