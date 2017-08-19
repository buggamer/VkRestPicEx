package com.example.daim.vkrestpicex.screen.auth;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.screen.gallery.GalleryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends AppCompatActivity implements AuthView{

    @BindView(R.id.login_web_view)
    WebView mWebView;

    private AuthPresenter mPresenter;

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, AuthActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        mWebView.setWebViewClient(new AuthWebClient());

        mPresenter = new AuthPresenter(this);
        mPresenter.init();
    }

    @Override
    public void loadAuthWebView(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void openGalleryScreen() {
        GalleryActivity.start(this);
        finish();
    }

    private class AuthWebClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mPresenter.saveParseToken(url);
        }
    }

}
