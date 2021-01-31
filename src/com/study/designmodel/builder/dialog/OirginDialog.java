package com.study.designmodel.builder.dialog;

/**
 * 模拟原生对话框，对话框具备以下几个要素：
 * 标题
 * 内容
 * 确定按钮， 取消按钮
 * 按钮影响事件
 */
public class OirginDialog {
    public String title;
    public String message;
    public OnClickListener posListener;
    public OnClickListener negtiveListener;

    public void show(){
        System.out.println("ShowDialog--" + this);
    }

    public void dismissDlg(){
        System.out.println("CloseDialog--" + this);
    }

    public void setPosListener(OnClickListener listener){
        this.posListener = listener;
    }

    public void setNegtiveListener(OnClickListener listener){
        this.negtiveListener = listener;
    }

    public void clickPositiveButton(){
        if (posListener!=null){
            posListener.onClick();
        }
    }

    public void clickNegtiveButton(){
        if (negtiveListener!=null){
            negtiveListener.onClick();
        }
    }

    interface OnClickListener{
        void onClick();
    }
}
