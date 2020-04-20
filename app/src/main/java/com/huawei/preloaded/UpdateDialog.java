package com.huawei.preloaded;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;


public class UpdateDialog extends AlertDialog {

    private Context mContext;

    private View mView;

    private OnAcceptCallBack onAcceptCallBack;

    public UpdateDialog(Context context) {
        super(context);
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(AppUtil.getResourceIdForEMUIX(mContext
                ,"update_dialog_content","layout"),null);
        setView(mView);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setTitle(R.string.update_dialog_title_app_update);
    }

    @Override
    public void show() {
        super.show();
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public UpdateDialog setAcceptCallBack(OnAcceptCallBack acceptCallBack){
        this.onAcceptCallBack = acceptCallBack;
        setButton(DialogInterface.BUTTON_POSITIVE, mContext.getString(R.string.update_dialog_btn_sure)
                , new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                if(onAcceptCallBack!=null){
                    onAcceptCallBack.onAcceptCallBack();
                }
            }
        });
        setButton(DialogInterface.BUTTON_NEGATIVE, mContext.getString(R.string.update_dialog_btn_cancel)
                , new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                if(onAcceptCallBack!=null){
                    onAcceptCallBack.onCancelCallBack();
                }
            }
        });
        return this;
    }

    public interface OnAcceptCallBack{
        void onAcceptCallBack();
        void onCancelCallBack();
    }
}
