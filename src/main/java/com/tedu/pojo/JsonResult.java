package com.tedu.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -6635614137616315928L;
    private Integer state = 1;
    private String message = "ok";
    private T data;

    private JsonResult(String message){
        this.message = message;
    }

    public static JsonResult<?> notFound() {
        return JsonResult.error("没有找到");
    }

    private JsonResult setErrorStatus(){
        this.state = 0;
        return this;
    }

    public JsonResult(T data){
        this.data = data;
    }

    public static JsonResult error(String message) {
        return new JsonResult(message).setErrorStatus();
    }

    public static JsonResult success(String message){
        return new JsonResult(message);
    }

}
