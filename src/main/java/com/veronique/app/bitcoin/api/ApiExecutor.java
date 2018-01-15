package com.veronique.app.bitcoin.api;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiExecutor {

    public String execute(String url) {
        try {

            HttpClient httpClient = new HttpClient();

            GetMethod method = new GetMethod(url);
            method.releaseConnection();
            httpClient.executeMethod(method);
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while((str = br.readLine()) != null){
                stringBuffer .append(str );
            }

            return stringBuffer.toString();
        } catch (Exception e) {

        }
        return null;
    }


}
