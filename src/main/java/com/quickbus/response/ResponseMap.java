package com.quickbus.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseMap {
    @JsonIgnore
    private HttpStatus code = HttpStatus.OK;
    private Object data;
    private String status = "Success";
    private String message;

    public ResponseMap(){
    }
   public ResponseMap success(Object data,String message){
       this.data = data;
       this.message = message;
       return this;
   }

   public ResponseMap error(HttpStatus code, String message){
       this.code = code;
       this.status = "Failed";
       this.message = message;
       return this;
   }

}
