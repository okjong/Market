package com.example.market;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GloabalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"7a45f387f57e2c74bee8a9686f5089bf");
    }
}
