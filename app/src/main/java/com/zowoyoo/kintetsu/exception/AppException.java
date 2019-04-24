package com.zowoyoo.kintetsu.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

//    private int code;
//    private String msg;

    public AppException(Object Obj) {
        super(Obj.toString());
    }

//
//    public AppException(String msg) {
//        this.msg = msg;
//    }
//
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
