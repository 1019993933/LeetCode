package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.IRequest;
import com.study.designmodel.Chain.request.LeaveApprovalRequest;

public class DepartmentHead extends Handler {

    @Override
    protected boolean handler(IRequest request) {
        if (request instanceof LeaveApprovalRequest){
            LeaveApprovalRequest leaveApprovalRequest = (LeaveApprovalRequest)request;
            if (leaveApprovalRequest.getDays()<=7){
                System.out.println("系主任批准请假！！");
                return true;
            }
        }
        return false;
    }
}
