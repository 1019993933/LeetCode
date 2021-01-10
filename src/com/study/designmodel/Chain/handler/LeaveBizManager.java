package com.study.designmodel.Chain.handler;

import com.study.designmodel.Chain.request.LeaveApprovalRequest;

/**
 * 请假业务处理逻辑对外接口，负责内部责任链的构建及请求处理
 */
public class LeaveBizManager {
    static class SingletonHoler{
        private static LeaveBizManager mInstance =new LeaveBizManager();
    }
    
    private LeaveBizManager(){
        
    }
    
    public static LeaveBizManager getInstance(){
        return SingletonHoler.mInstance;
    }
    
    public boolean handleLeaveRequest(LeaveApprovalRequest request){
        //以下三个角色实际上是根据请假请求中的学生信息，通过数据库查询动态生成的
        //并且在职业信息中就隐含了责任链上的下一个节点信息，即班主任信息中有对应的系主任，系主任有对应的院长
        //这些信息是由组织结构上就决定了的，此处模拟采用手动设置的方式

        //构建处理链
        ClassAdviser classAdviser = new ClassAdviser();
        DepartmentHead head = new DepartmentHead();
        Dean dean = new Dean();
        classAdviser.setNextHandler(head);
        head.setNextHandler(dean);

        //处理请求
        return classAdviser.handleRequest(request);
    }
}
