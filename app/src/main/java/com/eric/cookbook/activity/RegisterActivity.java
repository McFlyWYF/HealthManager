package com.eric.cookbook.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.cookbook.R;
import com.eric.cookbook.view.OwlView2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HTTP;

public class RegisterActivity extends AppCompatActivity {

//    OkHttpClient client = new OkHttpClient();

    private EditText account_et, password_et, age_et;
    private TextView login;
    private Button signUp;
    private OwlView2 owlView2;

//    private String result;

    public static int MYACTIVITY_FAILURE = 1;
    public static int MYACTIVITY_SUCCESS = 2;

    String strResult = null;
    Handler shandler;

    String success;
    private boolean signFlag;
    private String userName;
    private String password;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account_et = findViewById(R.id.signUp_account);
        password_et = findViewById(R.id.signUp_password);
        age_et = findViewById(R.id.age);

        login = findViewById(R.id.back_login_tv);
        signUp = findViewById(R.id.btn_register);

        owlView2 = findViewById(R.id.owl_view);
        owlView2.addWatchEditText(account_et);
        owlView2.addWatchEditText(password_et);
        owlView2.addWatchEditText(age_et);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (account_et.getText().toString().trim().isEmpty() || password_et.getText().toString().trim().isEmpty() || age_et.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请输入完整的信息", Toast.LENGTH_SHORT).show();
                    account_et.setText("");
                    password_et.setText("");
                    age_et.setText("");
                } else {
                    Thread signThread = new Thread(new SignHandler());
                    signThread.start();
                }
            }
        });

        shandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                signFlag = msg.getData().getBoolean("signFlag");
                if (signFlag) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shandler.removeCallbacksAndMessages(null);
    }

    class SignHandler implements Runnable {
        @Override
        public void run() {
            userName = account_et.getText().toString();
            password = password_et.getText().toString();
            age = age_et.getText().toString();
            String url = "http://39.105.110.19/register1/";
            boolean signState = validatelocalsign(userName, password, age, url);
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (signState) {
                bundle.putBoolean("signFlag", true);
                message.setData(bundle);
                shandler.sendMessage(message);
            } else {
                bundle.putBoolean("signFlag", false);
                message.setData(bundle);
                shandler.sendMessage(message);
            }
        }
    }

    private boolean validatelocalsign(String userName, String password, String age, String url) {
        Log.d("username", userName);
        Log.d("password", password);
        Log.d("age", age);

        boolean signState = false;
        HttpPost httpRequest = new HttpPost(url);
        List params = new ArrayList<>();
        params.add(new BasicNameValuePair("account", userName));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("age", age));

        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpClient httpClient = new DefaultHttpClient();
            HttpParams httpParams = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            httpResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            strResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject object = new JSONObject(strResult);
            success = object.getString("message");
            Log.d("message", success);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (success.equals("注册成功")) {
            signState = true;
        } else {
            signState = false;
        }
        return signState;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.back_login_tv:
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.btn_register:
//                if (account.getText().equals("") || password.getText().equals("") || age.getText().equals("")) {
//                    Toast.makeText(RegisterActivity.this, "请输入有效数字", Toast.LENGTH_SHORT).show();
//                    account.setText("");
//                    password.setText("");
//                    age.setText("");
//                }else {
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            String accountValue = account.getText().toString().trim();
//                            String passwordValue = password.getText().toString().trim();
//                            String ageValue = age.getText().toString().trim();
//
//                            String path = "http://39.105.110.19/register1/";
//
//                            RequestBody requestBody = new FormBody.Builder()
//                                    .add("account", accountValue)
//                                    .add("password", passwordValue)
//                                    .add("age", ageValue)
//                                    .build();
//
//                            Request request = new Request.Builder()
//                                    .url(path)
//                                    .post(requestBody)
//                                    .build();
//
//                            Call call = client.newCall(request);
//                            Response response = null;
//
//                            try {
//                                response = call.execute();
//                                String res = new String(response.body().bytes(), "gbk");
//                                showResponse(res);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }.start();
//                }
//
//                account.setText("");
//                password.setText("");
//                age.setText("");
//        }
//    }

//    private void showResponse(final String res) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(res);
//            result = jsonObject.getString("message");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
