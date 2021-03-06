package com.button.smart.smartbutton.Http;

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by charlie on 2017/6/12.
 */

public class Network_core{

    private static Context context;
    private OkHttpUtils mokHttpClient;
    private RequestCall mcall;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String SERVER_HOST = "http://140.123.92.229:4000";

    //!!注意 使用前需要實例化 Network_core()
    public Network_core(Context context)
    {
        this.context = context;
    }

    //取消目前的Call請求
    public void Cancel_network(){
        if(mcall!=null)
            mcall.cancel();
    }

    /*
    * TODO 會員系統操作_API ##############################
    * */

    /*
    * TODO Button_API ##############################
    * */
    /**
     * 查詢使用者所有的按鈕
     * @param user 使用者名稱
     * @return    void
     */

    public void button_findAll(String user) {
        Log.e("debug", "in button_findAll");
        String url = SERVER_HOST + "/findAll";
        Log.e("test url", url);
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("user", user)
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("error", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("button_findAll:", response);
                mCallback.response(response);
            }
        });
    }

    public void button_turn(String user, String bid, Boolean status) {
        String url = SERVER_HOST + "/turn";

        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("user", user)
                .addParams("bid", bid)
                .addParams("status", status.toString())
                .addParams("goto", "Gateway")
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("button_turn:", response);
                //mCallback.response(response);
            }
        });
    }

    public void button_add(String user, String bid, String group, String name, String description) {
        String url = SERVER_HOST + "/add";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("user", user)
                .addParams("bid", bid)
                .addParams("group", group)
                .addParams("name", name)
                .addParams("description", description)
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("error", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("button_add:", response);
                mCallback.response(response);
            }
        });
    }

    public void button_delete(String user, String bid) {
        String url = SERVER_HOST + "/delete";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("user", user)
                .addParams("bid", bid)
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("button_delete:", response);
                mCallback.response(response);
            }
        });
    }

    public void button_update(String user, String bid, String group, String name, String description) {
        String url = SERVER_HOST + "/update";
        mcall = mokHttpClient
                .post()
                .url(url)
                .addParams("user", user)
                .addParams("bid", bid)
                .addParams("group", group)
                .addParams("name", name)
                .addParams("description", description)
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("button_update:", response);
                mCallback.response(response);
            }
        });
    }


    /*
    * CallBack
    * */
    private netCallback mCallback;

    public interface netCallback{
        public abstract String response(String response);
    }

    public void setCallback(netCallback callback){
        this.mCallback = callback;
    }
}
