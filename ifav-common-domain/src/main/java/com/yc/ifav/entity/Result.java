package com.yc.ifav.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message=resultCode.message();
        this.data=data;
    }

    //返回成功
    public static Result success(){
        Result result=new Result(ResultCode.SUCCESS,null);
        return result;
    }

    public static Result success(Object data){
        Result result=new Result(ResultCode.SUCCESS,data);
        return result;
    }

    //返回失败
    public static Result failure(ResultCode resultCode){
        Result result=new Result(resultCode,null);
        return result;
    }

    public static Result failure(ResultCode resultCode,Object data){
        Result result=new Result(resultCode,data);
        return result;
    }
}
