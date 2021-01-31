package com.study.designmodel.builder.pattern;

/**
 * Builder模式有两类角色，一个是指导者Director, 一个是构建者Builder
 * 指导者负责组织构建逻辑，而由构建者实现具体的部件构建
 * 由于构建者模式通常是用于解决复杂对象的创建过程， 指导者通常由客户端进行实现
 * Builder模式的接口类，对应每一个具体实现需要实现的接口
 */
public interface IBuilder {
    String buildHead();

    String buildBody();

    String buildFoot();
}
