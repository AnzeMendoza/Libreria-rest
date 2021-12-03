package edu.sucho.libreriaweb.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
public class ResponseInfo {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int statusCode;
    @JsonProperty("path")
    private String uriRequested;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z", timezone="America/Argentina/Buenos_Aires")
    private Date timestamp;
    public ResponseInfo(int statusCode, String message, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.timestamp = new Date();
    }
    public String getMessage() {
        return message;
    }

}
