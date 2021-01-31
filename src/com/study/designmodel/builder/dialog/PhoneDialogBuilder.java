package com.study.designmodel.builder.dialog;

public class PhoneDialogBuilder extends MyDialog.Builder {
    @Override
    public MyDialog build() {
        this.myDialog = new MyDialog();
        initMyDialog();
        return this.myDialog;
    }
}
