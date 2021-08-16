package com.monetware.rctexperiment.system.base;

public enum ErrorCode {

	//Token验证
    TOKEN_WITHOUT(10001,"无效的凭证"),
	TOKEN_INVALID(10002,"TOKEN失效"),
    USER_NOT_LOGIN(10003,"用户未登陆"),

    //邮件相关
	EMAIL_SEND_FAIL(10004, "邮件发送失败"),

    //任务相关
	USER_TASK_EXIT(20001, "用户实验已存在"),
	USER_TASK_SAMPLE_EXIT(20002, "用户实验样本已存在"),
	USER_TASK_GROUP_RESULT_NULL(20003, "未选择分组");

	private String msg;
    private int code;

    ErrorCode(int code,String msg)
    {
    	this.code=code;
        this.msg=msg;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}

