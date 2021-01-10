package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.IRequest;
import com.study.designmodel.Chain.request.LeaveApprovalRequest;

public class ClassAdviser extends Handler {
    /**
     * 班主任处理不了，交给系主任
     * @return
     */
    @Override
    public IHandler getNextHandler() {
        return super.getNextHandler();
    }

    /**
     * 处理学生的请示，当前只支持请假
     * @param request
     * @return
     */
    @Override
    protected boolean handler(IRequest request) {
        if (request instanceof LeaveApprovalRequest){
            LeaveApprovalRequest leaveApprovalRequest = (LeaveApprovalRequest)request;
            if (leaveApprovalRequest.getDays()<=3){
                System.out.println("班主任批准请假！！");
                return true;
            }
        }
        return false;
    }
}
