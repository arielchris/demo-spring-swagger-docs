package com.example.demospringswaggerdocs.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    public Integer code;
    public String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object data;


    public ApiResponse(Integer code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static ApiResponse build(Integer code, String message, Object data){
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse build(Integer code, String message){
        return new ApiResponse(code, message);
    }

    public ApiResponse putDataMap(String attribute, Object object){
        if(this.data == null){
            this.data = new HashMap<String, Object>();
        }

        if(this.data instanceof HashMap<?,?>){
            ((HashMap<String, Object>) this.data).put(attribute, object);
        }
        return this;
    }
}
