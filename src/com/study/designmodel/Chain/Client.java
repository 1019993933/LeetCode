package com.study.designmodel.Chain;

import com.study.designmodel.Chain.handler.LeaveBizManager;
import com.study.designmodel.Chain.model.Student;
import com.study.designmodel.Chain.request.LeaveApprovalRequest;
import org.junit.jupiter.api.Test;

public class Client {

    @Test
    public void test() {
        Student student = new Student("zhangsan", 20);
        LeaveApprovalRequest request = new LeaveApprovalRequest(student, 3);
        LeaveBizManager.getInstance().handleLeaveRequest(request);

        LeaveApprovalRequest request2 = new LeaveApprovalRequest(student, 7);
        LeaveBizManager.getInstance().handleLeaveRequest(request2);

        LeaveApprovalRequest request3 = new LeaveApprovalRequest(student, 15);
        LeaveBizManager.getInstance().handleLeaveRequest(request3);

        LeaveApprovalRequest request4 = new LeaveApprovalRequest(student, 35);
        LeaveBizManager.getInstance().handleLeaveRequest(request4);
    }

}
