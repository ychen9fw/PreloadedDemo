package com.huawei.preloaded;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AppUtil {

    public static final String EMUI9 = "_emui9";

    public static boolean isEMuiVersion9(){
        return (Build.VERSION.SDK_INT >= 28);
    }

    public static int getResourceIdForEMUIX(Context context,String name,String deType){
        int resourceID = 0;
        if(isEMuiVersion9()){
            name = name + EMUI9;
        }
        resourceID = context.getResources().getIdentifier(name,deType,context.getPackageName());
        return resourceID;
    }

    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                String pn = pInfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }
}
