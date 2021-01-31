package com.study.designmodel.builder.dialog;

public abstract class DlgParam implements MyDialog.ParamBuilder {
    public String title;
    public String message;
    public MyDialog.IMyOnclickListener posListener;
    public MyDialog.IMyOnclickListener negtiveListener;
}
