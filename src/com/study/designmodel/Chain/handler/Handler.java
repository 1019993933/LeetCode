package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.IRequest;

/**
 * 处理类父类，抽象类，保存下一个节点成员
 */
public abstract class Handler implements IHandler {
    protected IHandler nextHandler;

    /**
     * 责任链的处理处理逻辑，当前能够处理则处理，不能处理则交由下一环节处理
     * @param request
     * @return
     */
    @Override
    public boolean handleRequest(IRequest request) {
        if (!handler(request))
        {
            //当前没有处理
            if (nextHandler!=null){
                //有下一个节点，交由下一个节点处理
                return nextHandler.handleRequest(request);
            }else{
                //返回失败
                System.out.println("请假申请失败，请假时间太长没人能批准本次请假！");
                return false;
            }
        }
        //返回成功
        return true;
    }

    /**
     * 在有成员保存下一个节点的情况下，可以直接返回
     * 也可以不采用成员，而是通过请求与业务逻辑动态生成下一个处理节点
     * @return
     */
    protected IHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(IHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    /**
     * 抽象方法，具体处理方法，由具体对象各自实现
     * @param request
     * @return
     */
    protected abstract boolean handler(IRequest request);
}
