package com.oceansoft.osc.http.rxjavahttp;





import com.oceansoft.osc.config.BaseApplication;
import com.oceansoft.osc.config.Config;
import com.oceansoft.osc.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TempCw on 2017/1/11.
 */

public class ApiManager {
    private static ApiManager apiManager=null;
    private File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDir(), "httpcache");
    private int CacheSize=10*1024*1024;
    private Cache cache=new Cache(httpCacheDirectory,CacheSize);
    //https请求
    public OkHttpClient getOkhttpsClient() {
            try {
                KeyStore trustStore = KeyStore.getInstance(KeyStore
                        .getDefaultType());
                trustStore.load(null, null);
                // 设置 https支持
                final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] chain,
                            String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] chain,
                            String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }};
                SSLContext sslContext= SSLContext.getInstance("SSL");
                sslContext.init(null,trustAllCerts,new java.security.SecureRandom());
                javax.net.ssl.SSLSocketFactory sslSocketFactory=sslContext.getSocketFactory();


              OkHttpClient  client = new OkHttpClient.Builder()
                        .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                        .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                        .cache(cache)
                        .sslSocketFactory(sslSocketFactory)       //支持Https
                        .build();

                return client;
            }catch (Exception e){
                e.printStackTrace();
            }


        return null;
    }
    //Http请求
    public OkHttpClient getOkhttpClient(){
        OkHttpClient  client = new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();
        return client;
    }

//
//    private  OkHttpClient client=new OkHttpClient.Builder()
//            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//            .cache(cache)
//            .build();


    private MainApi mainApi;
    private Object object=new Object();

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(BaseApplication.getInstance())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
    public static ApiManager getInstance(){
        if (apiManager==null){
            synchronized (ApiManager.class){
                if (apiManager==null){
                    apiManager=new ApiManager();
                }
            }
        }
        return apiManager;
    }
    //Https
    public MainApi getMainService(){
        if (mainApi==null){
            synchronized (object){
                if (mainApi==null){
                    mainApi=new Retrofit.Builder()
                            .baseUrl(Config.HOST)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getOkhttpsClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(MainApi.class);
                }
            }
        }
        return mainApi;
    }
    //http
    public MainApi getHttpMainService(){
        if (mainApi==null){
            synchronized (object){
                if (mainApi==null){
                    mainApi=new Retrofit.Builder()
                            .baseUrl(Config.HOSTHttp)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getOkhttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(MainApi.class);
                }


            }

        }
        return mainApi;
    }


}
