package com.zhibeifw.frameworks.dagger;

import com.qiniu.android.storage.UploadManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/8/1 0001.
 */
@Module
public class QiniuModule {

    @Provides
    UploadManager provideUploadManager() {
        return new UploadManager();
    }
}
