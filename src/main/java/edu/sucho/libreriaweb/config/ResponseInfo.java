package edu.sucho.libreriaweb.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
public class ResponseInfo {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int statusCode;
    @JsonProperty("path")
    private String uriRequested;
    @JsonProperty("timestamp")
    private String timestamp;
    

    public ResponseInfo(Exception exception, String uriRequested) {
        this.message = exception.getMessage();
        // this.statusCode = exception.getStatusCode().value();
        this.uriRequested = uriRequested;
    }

    public ResponseInfo(int statusCode, String message, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.timestamp = getTimeResposeInfo().toString();
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

    private Date getTimeResposeInfo() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        System.out.println(calendar.getTime());
        return calendar.getTime();
        /*String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss a";
        Calendar cal = Calendar.getInstance();

        //cal.set(2017, 06, 29, 8, 30);
        Date date = cal.getTime();

        // format with tz
        TimeZone timeZone = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");
        SimpleDateFormat formatterWithTimeZone = new SimpleDateFormat(DATE_FORMAT);

        formatterWithTimeZone.setTimeZone(timeZone);

        // change tz using formatter
        String sDate = formatterWithTimeZone.format(date);

        // string to object date
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date dateWithTimeZone;
        try{
            dateWithTimeZone = formatter.parse(sDate);
            return dateWithTimeZone;
        }catch(ParseException e){
            new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"PATH");
        }
        return null;*/
    }
}
