package com.study.designmodel.builder.pattern;

public class XmlBuilder implements IBuilder {
    @Override
    public String buildHead() {
        return "<This is Head>";
    }

    @Override
    public String buildBody() {
        return "<This is Body>";
    }

    @Override
    public String buildFoot() {
        return "<This is Foot>";
    }
}
