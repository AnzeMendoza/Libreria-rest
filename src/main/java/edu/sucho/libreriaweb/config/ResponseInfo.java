package edu.sucho.libreriaweb.config;

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
    private Date timestamp;

    public ResponseInfo(Exception exception, String uriRequested) {
        this.message = exception.getMessage();
        this.uriRequested = uriRequested;
    }

    public ResponseInfo(int statusCode, String message, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.timestamp = new Date();
    }

    public ResponseInfo(int statusCode, String message, String uriRequested, Date timestamp) {
        setMessage(message);
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
    private void setMessage(String message) {
        this.message = message;
    }

    public String getUriRequested() {
        return uriRequested;
    }
}
