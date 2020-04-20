package com.huawei.preloaded;

public final class UpdateConfig {
    /*
     * app store package name
     * huawei store: 'com.huawei.appmarket'
     * google play store: 'com.android.vending'
     */
    public final static String APP_STORE_NAME = "com.android.vending";
    /*
     * app store deeplink
     * huawei store deeplink: 'hiapplink://com.huawei.appmarket?appId=${appId}&accessID=${accessID}'
     * google play store deeplink: 'market://details?id=${app package name}'
     */
    public final static String APP_DEEP_LINK_STORE_URL = "market://details?id=com.ss.android.ugc.trill";
    /*
     * app h5 download url
     * huawei: 'https://appgallery.cloud.huawei.com/marketshare/app/${appId}'
     * google play: 'https://play.google.com/store/apps/details?id=${app package name}'
     */
    public final static String APP_H5_DOWNLOAD_URL = "https://play.google.com/store/apps/details?id=com.ss.android.ugc.trill";
}
