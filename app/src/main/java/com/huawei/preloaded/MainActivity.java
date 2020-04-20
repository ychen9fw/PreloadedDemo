package com.huawei.preloaded;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showUpdateDialog();
    }

    private void showUpdateDialog() {
        new UpdateDialog(this).setAcceptCallBack(new UpdateDialog.OnAcceptCallBack() {
            @Override
            public void onAcceptCallBack() {
                launchUpdate(UpdateConfig.APP_STORE_NAME, UpdateConfig.APP_DEEP_LINK_STORE_URL, UpdateConfig.APP_H5_DOWNLOAD_URL);
                finish();
            }

            @Override
            public void onCancelCallBack() {
                finish();
            }
        }).show();
    }

    private void launchUpdate(String appStoreName, String appStoreUrl, String appH5DownloadUrl) {
        if (AppUtil.isInstalled(getApplicationContext(), appStoreName)) {
            launchAppStore(appStoreName, appStoreUrl, appH5DownloadUrl);
        } else {
            launchBrowser(appH5DownloadUrl);
        }
    }

    private void launchBrowser(String appH5DownloadUrl) {
        try {
            Log.e(TAG, "launch browser success "+ appH5DownloadUrl);
            Uri uri = Uri.parse(appH5DownloadUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            Log.w(TAG, "launch browser exception!");
        }
    }

    private void launchAppStore(String appStoreName, String appStoreUrl, String appH5DownloadUrl) {
        try {
            Uri uri = Uri.parse(appStoreUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(appStoreName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            if (isIntentAvailable(intent)) {
                startActivity(intent);
                Log.w(TAG, "launch app store success!");
            } else {
                launchBrowser(appH5DownloadUrl);
                Log.w(TAG, "launch app store deeplink exception!");
            }
        } catch (Exception e) {
            Log.w(TAG, "launch app store exception!");
        }
    }

    public boolean isIntentAvailable(final Intent intent) {
        final PackageManager packageManager = getPackageManager();
        if(intent!=null){
            List<ResolveInfo> resolveInfo =
                    packageManager.queryIntentActivities(intent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            return resolveInfo!=null && resolveInfo.size()> 0;
        }else{
            return false;
        }
    }
}
