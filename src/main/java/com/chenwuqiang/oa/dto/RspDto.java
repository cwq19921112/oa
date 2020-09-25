package com.chenwuqiang.oa.dto;

public class RspDto {
    private String code;
    private String message;

    public RspDto() {
    }

    public RspDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RspDto buildSuccess() {
        return new RspDto("success", null);
    }

    public static RspDto buildFail(String message) {
        return new RspDto("fail", message);
    }
}
