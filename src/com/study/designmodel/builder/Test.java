package com.study.designmodel.builder;

import com.study.designmodel.builder.dialog.MyDialog;
import com.study.designmodel.builder.dialog.OirginDialog;

public class Test extends MyDialog{

    @org.junit.jupiter.api.Test
    public void test() {
        MyDialog myDialog = new MyDialog();
        myDialog.dlg = new OirginDialog();

        this.dlg = new OirginDialog();
    }
}
