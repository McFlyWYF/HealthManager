package com.eric.cookbook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.cookbook.R;
import com.eric.cookbook.ui.MainActivity;
import com.eric.cookbook.view.OwlView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

//    OkHttpClient client = new OkHttpClient();

    private OwlView owlView;
    private EditText account_et;
    private EditText password_et;
    private Button login;
    private TextView signUp;

    private String userName;
    private String password;

    private boolean loginFlag;
    Handler handler;

    private String strResult = null;

    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        owlView = findViewById(R.id.owl_view);
        account_et = findViewById(R.id.account);
        password_et = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.register_tv);

        password_et.setOnFocusChangeListener(focusChangeListener);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account_et.getText().toString().trim().isEmpty() || password_et.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入完整的信息",Toast.LENGTH_SHORT).show();
                    account_et.setText("");
                    password_et.setText("");
                }else {
                    Thread loginThread = new Thread(new LoginHandler());
                    loginThread.start();

                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                loginFlag = msg.getData().getBoolean("loginFlag");
                if (loginFlag) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    class LoginHandler implements Runnable {
        @Override
        public void run() {
            userName = account_et.getText().toString();
            password = password_et.getText().toString();
            String url = "http://39.105.110.19/login1/";
            boolean loginState = validatelocalLogin(userName, password, url);
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (loginState) {
                bundle.putBoolean("loginFlag", true);
                message.setData(bundle);
                handler.sendMessage(message);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                bundle.putBoolean("loginFlag", false);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }
    }

    private boolean validatelocalLogin(String userName, String password, String url) {
        boolean loginState = false;
        HttpPost httpRequest = new HttpPost(url);
        List params = new ArrayList<>();
        params.add(new BasicNameValuePair("account", userName));
        params.add(new BasicNameValuePair("password", password));
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpClient httpClient = new DefaultHttpClient();
            HttpParams httpParams = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            httpResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"));
            String str = reader.readLine();
            reader.close();
            strResult = str;
            JSONObject object = new JSONObject(strResult);
            flag = object.getString("message");
            Log.d("message",flag);
            String message = new String(object.getString("message").getBytes("iso-8859-1"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag.equals("登录成功")) {
            loginState = true;
        } else {
            loginState = false;
        }
        return loginState;
    }


//    private void showResponse(final String responseData) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(responseData);
//            result = jsonObject.getString("message");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
//                if (result.equals("登录成功")) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
//
//    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (b) {
                owlView.open();
            } else {
                owlView.close();
            }
        }
    };

//    private View.OnClickListener loginListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.btn_login:
//                    if (account.getText().equals("") || password.getText().equals("")){
//                        Toast.makeText(LoginActivity.this,"请输入完整的注册信息",Toast.LENGTH_SHORT).show();
//                        account.setText("");
//                        password.setText("");
//                    }else {
//                        new Thread(){
//                            @Override
//                            public void run() {
//                                String accountValue = account.getText().toString().trim();
//                                String passwordValue = password.getText().toString().trim();
//
//                                String path = "http://39.105.110.19/login1/";
//                                RequestBody requestBody = new FormBody.Builder()
//                                        .add("account",accountValue)
//                                        .add("password",passwordValue)
//                                        .build();
//
//                                Request request = new Request.Builder()
//                                        .url(path)
//                                        .post(requestBody)
//                                        .build();
//
//                                Call call = client.newCall(request);
//                                Response response = null;
//
//                                try {
//                                    response = call.execute();
//                                    String res = new String(response.body().bytes(),"gbk");
//                                    showResponse(res);
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//                        }.start();
//                        account.setText("");
//                        password.setText("");
//                    }
//            }
//        }
//    };
}
