package com.drug.model.response;
import com.drug.enums.EnumCodes;
public class ResponseMessage {
    /*
        是否成功
     */
    private int code;

    /*
        返回信息
     */
    private String msg;

    /*
        返回数据
    */
    private Object data;

    public ResponseMessage(){
        int code= EnumCodes.UNKNOWN_ERROR;
        this.msg="";
        this.data=null;

    }
    public ResponseMessage(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
