package com.study.designmodel.builder.pattern;

public class Director {
    private IBuilder builder;

    /**
     * 如果具体的构建逻辑比较复杂的话，指导器本身又可以抽象为工厂方法来实现
     * @param type
     */
    public Director(int type){
        switch (type){
            case 1:
                builder = new TxtBuilder();
                break;
            case 2:
                builder = new XmlBuilder();
                break;
            default:
                builder = new TxtBuilder();
        }
    }

    public String getProduct(){
        StringBuffer product = new StringBuffer();
        product.append(builder.buildHead()).append("\n");
        product.append(builder.buildBody()).append("\n");
        product.append(builder.buildFoot()).append("\n");
        return product.toString();
    }
}
