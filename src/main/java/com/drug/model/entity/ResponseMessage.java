package com.drug.model.entity;

public class ResponseMessage {
    /*
        是否成功
     */
    private boolean success;

    /*
        返回信息
     */
    private String msg;

    /*
        返回数据
    */
    private Object data;

    public ResponseMessage(boolean success,String msg,Object data){
        this.success=success;
        this.msg=msg;
        this.data=data;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
