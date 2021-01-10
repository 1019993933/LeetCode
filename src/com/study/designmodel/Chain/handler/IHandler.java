package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.IRequest;

/**
 * 抽象处理接口，实现处理及获取下一个处理对象的接口
 */
public interface IHandler {
    /**
     * 处理当前请示，此处提handleRequest可以进行扩展，通过request的请示类型不同而调用不同的请示方法
     *
     * @param request
     * @return 是否已处理
     */
    boolean handleRequest(IRequest request);
}
