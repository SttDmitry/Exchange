package com.base.exchange.repo;


import android.os.AsyncTask;

import com.base.exchange.util.URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRepo {
    private static URL url;
    private static HttpURLConnection httpURLConnection;

    static {
        try {
            url = new URL(URLs.EXCHANGE_URL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void getData(ExchangeCallback exchangeCallback) throws IOException {
        AsyncTask.execute(()->{
            StringBuilder respBody = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                reader.lines().forEach(l -> respBody.append(l.trim()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            exchangeCallback.getExchangeInfo(respBody.toString(), false);
        });
    }
}
