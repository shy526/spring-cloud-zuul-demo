package com.demo.zuulservice.fallback;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Administrator
 */
public class MyClientHttpResponse implements ClientHttpResponse {

    private  String body;
    private final HttpStatus httpStatus;
    private final  HttpHeaders headers;

    public MyClientHttpResponse(String body, HttpStatus httpStatus,HttpHeaders headers) {
        this.body = body;
        this.httpStatus = httpStatus;
        this.headers = headers;
    }

    public static ClientHttpResponse getJsonResponse(String body){
        HttpHeaders headers = new HttpHeaders();
        MediaType json = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(json);
       return new MyClientHttpResponse(body,HttpStatus.OK,headers);
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return httpStatus;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return httpStatus.value();
    }

    @Override
    public String getStatusText() throws IOException {
        return httpStatus.getReasonPhrase();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        if (StringUtils.isEmpty(body)) {
            body = "";
        }
        return new ByteArrayInputStream(body.getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
