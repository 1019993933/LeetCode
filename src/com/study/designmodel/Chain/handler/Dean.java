package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.IRequest;
import com.study.designmodel.Chain.request.LeaveApprovalRequest;

public class Dean extends Handler {
    @Override
    protected boolean handler(IRequest request) {
        if (request instanceof LeaveApprovalRequest){
            LeaveApprovalRequest leaveApprovalRequest = (LeaveApprovalRequest)request;
            if (leaveApprovalRequest.getDays()<=15){
                System.out.println("院长批准请假！！");
                return true;
            }
        }
        return false;
    }
}
