package com.example.appstore.retrofit;

import com.example.appstore.util.SSLSocketClient;


import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {
    public static  String baseUrl = "https://192.168.143.187:8000/";

    private static RetrofitUtil retrofitUtil;

    private HashMap<String,Retrofit> retrofitMap = new HashMap<>();
//
//    private Retrofit retrofit;

    //NONE
    //BASIC
    //HEADERS
    //BODY
    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);
    private OkHttpClient client ;

    public Retrofit getRetrofit(){
        if(client ==null){
            client = new OkHttpClient.Builder().addInterceptor(logging)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .build();
        }
        Retrofit retrofit = retrofitMap.get(baseUrl);
        synchronized (Retrofit.class){
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                retrofitMap.put(baseUrl, retrofit);
            }
        }
        return retrofit;
    }
    public Retrofit getRetrofit(String baseUrl) {
        if(client ==null){
            client = new OkHttpClient.Builder().addInterceptor(logging)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .build();
        }
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    public static RetrofitUtil getInstance(){
        if (retrofitUtil ==null){
            synchronized (RetrofitUtil.class){
                if(retrofitUtil ==null){
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }
    public  RetrofitUtil setBaseUrl(String baseUrl) {
        RetrofitUtil.baseUrl = baseUrl;
        return this;
    }
}
