package com.study.designmodel.builder.dialog;

public class MyDialog {
    public OirginDialog dlg;

    public void show(){
        if (dlg!=null) dlg.show();
    }

    public void dismissDlg(){
        if(dlg!=null) dlg.dismissDlg();
    }

    interface IMyOnclickListener{
        void onClick();
    }

    interface ParamBuilder{
        DlgParam build();
    }

    static abstract class Builder{
        DlgParam param;

        protected OirginDialog dlg;

        protected MyDialog myDialog;

        public abstract MyDialog build();

        protected void initMyDialog(){
            dlg.title = param.title;
            dlg.message = param.message;
            dlg.setPosListener(()->{
                param.posListener.onClick();
            });
            dlg.setNegtiveListener(()->{
                param.negtiveListener.onClick();
            });
        }
    }
}
