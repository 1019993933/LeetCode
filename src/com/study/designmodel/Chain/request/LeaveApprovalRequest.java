package com.study.designmodel.Chain.request;

import com.study.designmodel.Chain.model.Student;
import com.sun.istack.internal.NotNull;

/**
 * 请假请求
 */
public class LeaveApprovalRequest extends AbstractRequest {
    public Student student;

    /**
     * 请假天数
     */
    private int days;

    public LeaveApprovalRequest(@NotNull  Student student, int days){
        this.student = student;
        this.days = days;
        super.type = RequestType.Leave;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
