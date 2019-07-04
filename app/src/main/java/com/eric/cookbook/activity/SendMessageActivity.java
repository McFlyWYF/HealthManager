package com.eric.cookbook.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.eric.cookbook.R;
import com.eric.cookbook.fragment.MessageFragment;
import com.eric.cookbook.fragment.SendFragement;
import com.eric.cookbook.utils.CrashHandler;
import com.eric.cookbook.utils.Util;
import com.githang.statusbar.StatusBarCompat;

import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.Iterator;

public class SendMessageActivity extends Activity implements SendFragement.SendfraInterface, MessageFragment.MsgFrgOnclick {

    private MessageFragment messageFragment;
    private FragmentManager fm;
    public LocationClient mLocationClient = null;
    public int phoneNum = 0;
    private SharedPreferences mSharedPreferences = null;
    private Boolean isHaveSetting = false;
    private Boolean isChooseModify = false;
    private Boolean mHasSend = false;   //防止多次定位导致多次发送短信

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //首次运行显示闪屏（暂时不加闪屏）
//        SharedPreferences sharedPreferences = this.getSharedPreferences("config", this.MODE_PRIVATE);
//        if(sharedPreferences.getBoolean("isFirst", true)){
//            Util.printLog("首次运行显示闪屏");
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("isFirst", false);
//            editor.commit();
//            //Intent intent = new Intent(this, SplashActivity.class);
//            //startActivity(intent);
//            Toast.makeText(this, "首次启动时间较长，请耐心等待", Toast.LENGTH_SHORT).show();
//        }
        //在Appliction里设置异常处理器为UncaughtExceptionHandler处理器，防止崩溃
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sos);
        requestPermission();
        StatusBarCompat.setStatusBarColor(SendMessageActivity.this, Color.WHITE);  //状态栏颜色
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        SendFragement sendFragment = new SendFragement();
        ft.replace(R.id.fra_send, sendFragment);
        ft.commit();
        PgyCrashManager.register(this); //集成蒲公英crash sdk
        //定位相关
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                switch (bdLocation.getLocType()){
                    case 61:
                        Util.printLog("GPS定位成功");
                        break;
                    case 62:
                        Util.printLog("定位失败，错误62");
                        break;
                    case 63:
                        Util.printLog("定位失败，错误63");
                        break;
                    case 65:
                        Util.printLog("定位失败，错误65");
                        break;
                    case 66:
                        Util.printLog("定位失败，错误66");
                        break;
                    case 67:
                        Util.printLog("定位失败，错误67");
                        break;
                    case 68:
                        Util.printLog("定位失败，错误68");
                        break;
                    case 161:
                        Util.printLog("网络定位成功");
                        break;
                    case 162:
                        Util.printLog("定位失败，错误162");
                        break;
                    case 167:
                        Util.printLog("定位失败，错误167");
                        break;
                    case 502:
                        Util.printLog("定位失败，错误502");
                        break;
                    case 505:
                        Util.printLog("定位失败，错误505");
                        break;
                    case 601:
                        Util.printLog("定位失败，错误601");
                        break;
                    case 602:
                        Util.printLog("定位失败，错误602");
                        break;
                    case 501:
                        Util.printLog("定位失败，错误501");
                        break;
                    default:
                        Util.printLog("定位失败，无错误码");
                        break;
                }

                Util.printLog("定位结果：province==" + bdLocation.getAddrStr());
                Util.printLog("定位结果：city==" + bdLocation.getCity());
                Util.printLog("定位结果：district==" + bdLocation.getDistrict());
                Util.printLog("定位结果：street==" + bdLocation.getStreet());
                Util.printLog("定位结果：streetNumber==" + bdLocation.getStreetNumber());
                Util.printLog("定位结果：address==" + bdLocation.getAddrStr());
                Util.printLog("定位结果：coorType==" + bdLocation.getCoorType());
                Util.printLog("定位结果：longitude==" + "" + bdLocation.getLongitude());
                Util.printLog("定位结果：latitude==" + "" + bdLocation.getLatitude());
                Util.printLog("定位结果：type==" + bdLocation.getCoorType());
                //Util.printLog("定位结果：poiList==" + bdLocation.getPoiList());
                Util.printLog("定位结果：locationDescribe==" + bdLocation.getLocationDescribe());

                if(bdLocation.getLocType() == 61 || bdLocation.getLocType() == 161){
                    Util.printLog("获取位置成功");
                    String msgLocation = "。我在：" + bdLocation.getAddrStr().substring(2, bdLocation.getAddrStr().length()) + "，"
                            + bdLocation.getLocationDescribe().substring(1, bdLocation.getLocationDescribe().length())
                            + "（经度：" + bdLocation.getLongitude() + "，纬度：" +
                            bdLocation.getLatitude() + "）";
                    if(!mHasSend){
                        mHasSend = true;
                        makeMessageToSend(msgLocation);
                    }else {
                        Util.printLog("重复定位，已阻止多次发送短信");
                    }
                } else {
                    Util.printLog("获取位置失败，短信将不包含位置信息");
                    Toast.makeText(SendMessageActivity.this, "获取位置失败，短信将不包含位置信息", Toast.LENGTH_SHORT).show();
                    makeMessageToSend("");
                }
            }
        });
        initLocation(); //注册监听函数
    }

    @Override
    public void sendOnClick() {
        Util.printLog("点击发送按钮");
        mHasSend = false;
        mSharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
        final String phone1ToSend = mSharedPreferences.getString("phone1", "");
        final String phone2ToSend = mSharedPreferences.getString("phone2", "");
        if(phone1ToSend.trim() == "" && phone2ToSend.trim() == ""){
            Toast.makeText(this, "请先设置求救短信及接收短信手机",Toast.LENGTH_SHORT).show();
            return;
        }
        Boolean flag = true;
        if(!checkPermission("android.permission.ACCESS_COARSE_LOCATION")){
            Util.printLog("没有网络定位权限");
            flag = false;
        }
        if(!checkPermission("android.permission.ACCESS_FINE_LOCATION")){
            Util.printLog("没有GPS定位权限");
            flag = false;
        }
        if(!flag){
            //Toast.makeText(this, "没有获取位置权限，短信将不包含位置信息", Toast.LENGTH_SHORT);
        }
        mLocationClient.stop();
        mLocationClient.start();
    }

    private void makeMessageToSend(String location){
        mSharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
        String msgToSend = mSharedPreferences.getString("message", "");
        final String phone1ToSend = mSharedPreferences.getString("phone1", "");
        final String phone2ToSend = mSharedPreferences.getString("phone2", "");
        if(phone1ToSend.trim() == "" && phone2ToSend.trim() == ""){
            Toast.makeText(this, "请先设置求救短信及接受短信手机",Toast.LENGTH_SHORT).show();
            return;
        }
        msgToSend = msgToSend + location;
        Util.printLog("发送信息：" + "msgToSend==" + msgToSend + " phone1ToSend==" + phone1ToSend + " phone2ToSend==" + phone2ToSend);
        sendMessage(SendMessageActivity.this, phone1ToSend, phone2ToSend, msgToSend);
    }

    private void sendMessage(final Activity activity, String phone1, String phone2, String message){
        PendingIntent sendIntent = PendingIntent.getBroadcast(this, 0, new Intent("SENT_SMS_ACTION"), 0);
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Util.printLog("进到发送回调");
                if(Activity.RESULT_OK == getResultCode()){
                    Util.printLog("发送成功");
                    Toast.makeText(activity, "发送成功", Toast.LENGTH_SHORT).show();
                }
            }
        }, new IntentFilter("SENT_SMS_ACTION"));
        PendingIntent deliverIntent = PendingIntent.getBroadcast(this, 0, new Intent("DELIVERED_SMS_ACTION"), 0);
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(activity, "接收成功", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter("DELIVERED_SMS_ACTION"));
        SmsManager smsManager = SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        ArrayList<PendingIntent> sendIntentList = new ArrayList<>();
        sendIntentList.add(sendIntent);
        ArrayList<String> phone = new ArrayList<>();
        if(phone1.trim().replace(" ", "").length() == 0){
            phone.add(phone2);
            Util.printLog("即将向号码" + phone2 + "发送短信，短信内容：" + message);

        }else if(phone2.trim().replace(" ", "").length() == 0){
            phone.add(phone1);
            Util.printLog("即将向号码" + phone1 + "发送短信，短信内容：" + message);
        }else {
            phone.add(phone1);
            phone.add(phone2);
            Util.printLog("即将向号码" + phone1 + "和" + phone2 + "发送短信，短信内容：" + message);
        }
        Iterator<String> iterator = phone.iterator();
        while (iterator.hasNext()){
            if(message.length() > 70){
                ArrayList<String> phoneList = smsManager.divideMessage(message);
                smsManager.sendMultipartTextMessage(iterator.next(), null, phoneList, sendIntentList, null);
            }else {
                smsManager.sendTextMessage(iterator.next(), null, message, sendIntent, deliverIntent);
            }
        }
    }

    @Override
    public void setOnClick() {
        isChooseModify = false; //重新点击设置，设为未修改状态
        mSharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
        String messageGet = mSharedPreferences.getString("message", "");
        String phone1Get = mSharedPreferences.getString("phone1", "");
        String phone2Get = mSharedPreferences.getString("phone2", "");

        //已有设置则显示内容
        if(messageGet.trim() != "" && (phone1Get.trim() != "" || phone2Get.trim() != "")){
            Util.printLog("已设置短信，需显示已设置内容");
            isHaveSetting = true;
        }else {
            Util.printLog("没有设置短信");
            isHaveSetting = false;
        }
        messageFragment = new MessageFragment();
        Util.printLog("传入设置内容：" + "messageGet==" + messageGet + "，phone1Get==" + phone1Get + "，phone2Get=="
                + phone2Get + "，isHaveSetting==" + isHaveSetting + "");
        Bundle bundle = new Bundle();
        bundle.putBoolean("isHaveSetting", isHaveSetting);
        bundle.putString("messageGet", messageGet);
        bundle.putString("phone1Get", phone1Get);
        bundle.putString("phone2Get", phone2Get);
        messageFragment.setArguments(bundle);
        messageFragment.setCancelable(false);
        messageFragment.setStyle(Window.FEATURE_NO_TITLE, R.style.DialogTheme);
        messageFragment.show(getFragmentManager(), "MessageFragment");
    }


    @Override
    public void msgCloseOnClick() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(messageFragment);
        messageFragment = null;
        ft.commit();
        if(isChooseModify){
            Toast.makeText(this, "取消修改", Toast.LENGTH_SHORT).show();
        }
        if(!isHaveSetting){
            Toast.makeText(this, "取消设置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void commitOnClick() {
        Button btnCommit = (Button)messageFragment.getView().findViewById(R.id.btn_commit);
        EditText messageInput = (EditText)messageFragment.getView().findViewById(R.id.message_input);
        EditText editPhone1 = (EditText)messageFragment.getView().findViewById(R.id.edit_phone1);
        EditText editPhone2 = (EditText)messageFragment.getView().findViewById(R.id.edit_phone2);
        ImageView imagePhone1 = (ImageView)messageFragment.getView().findViewById(R.id.image_phone1);
        ImageView imagePhone2 = (ImageView)messageFragment.getView().findViewById(R.id.image_phone2);
        if(btnCommit.getText() == getResources().getString(R.string.btn_commit_modify)){
            isChooseModify = true;
            mSharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
            String messageGet = mSharedPreferences.getString("message", "");
            String phone1Get = mSharedPreferences.getString("phone1", "");
            String phone2Get = mSharedPreferences.getString("phone2", "");
            messageInput.setEnabled(true);
            editPhone1.setEnabled(true);
            editPhone2.setEnabled(true);
            imagePhone1.setEnabled(true);
            imagePhone2.setEnabled(true);
            messageInput.setText(messageGet);
            editPhone1.setText(phone1Get);
            editPhone2.setText(phone2Get);
            editPhone1.setHint(getResources().getString(R.string.phone_input_hint));
            editPhone2.setHint(getResources().getString(R.string.phone_input_hint));
            messageInput.setBackgroundColor(getResources().getColor(R.color.edit_message_common));
            btnCommit.setText(getResources().getString(R.string.btn_commit_sure));
        }else {
            if(messageInput == null || messageInput.getText().toString().trim().length() == 0){
                Toast.makeText(this, "请输入短信内容", Toast.LENGTH_SHORT).show();
            }else if((editPhone1 == null || editPhone1.getText().toString().trim().length() == 0) &&
                    (editPhone2 == null || editPhone2.getText().toString().trim().length() == 0)){
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            }else {
                String phone1 = editPhone1.getText().toString();
                String phone2 = editPhone2.getText().toString();
                if(((phone2.trim().length() == 0 && phone1.trim().length() != 0) && !isMobileNO(phone1)) ||
                        ((phone1.trim().length() == 0 && phone2.trim().length() != 0) && !isMobileNO(phone2)) ||
                        ((phone1.trim().length() != 0) && (phone2.trim().length() != 0) && !(isMobileNO(editPhone1.getText().toString()) && isMobileNO(editPhone2.getText().toString())))){
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    saveInfo(messageInput.getText().toString(), editPhone1.getText().toString(), editPhone2.getText().toString());
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.remove(messageFragment);
                    messageFragment = null;
                    ft.commit();
                    Toast.makeText(this, "设置完成", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void msgBackOnClick() {
        Util.printLog("按下返回键");
        msgCloseOnClick();
    }

    @Override
    public void phoneList1OnClick() {
        phoneNum = 1;
        askContractPermission();
    }

    @Override
    public void phoneList2OnClick() {
        phoneNum = 2;
        askContractPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //6.0系统处理请求权限结果
        if(requestCode == 123){
            //用户授予权限
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            }else {
                Toast.makeText(this, "已拒绝授予通讯录权限，请在 设置—应用 中开启，或手动输入手机号码", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void saveInfo(String message, String phone1, String phone2){
        //获取SharedPreferences对象
        SharedPreferences sharedPre = this.getSharedPreferences("config", this.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        editor.putString("message", message);
        editor.putString("phone1", phone1);
        editor.putString("phone2", phone2);
        //提交
        editor.commit();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void askContractPermission(){
        if(checkPermission("android.permission.READ_CONTACTS")){
            Util.printLog("有通讯录权限");
            startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
        }else {
            Util.printLog("没有通讯录权限");
            //6.0版本弹出请求权限提示
            if(Integer.parseInt(Build.VERSION.SDK) == 23){
                this.requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS},
                        123);
            } else {
                new AlertDialog.Builder(this).setTitle("提示")
                        .setMessage("没有通讯录权限，请在 设置—应用 中授予通讯录权限，或者手动输入手机号码")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                dialog.dismiss();
                            }
                        }).show();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Util.printLog("有通讯录权限");
        String usernumber = "";
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = getContentResolver();
            Uri contactData = data.getData();
            @SuppressWarnings("deprecation")
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();
            String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);
            while (phone.moveToNext()) {
                usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            Util.printLog("获取联系人：" + usernumber + "（" + username + "）");
            if(messageFragment != null && (username != "" || usernumber != "")){
                EditText editText = (EditText)((1 == phoneNum) ? messageFragment.getView().findViewById(R.id.edit_phone1):
                        messageFragment.getView().findViewById(R.id.edit_phone2));
                editText.setText(usernumber.trim().replace(" ",""));
                Toast.makeText(this, "已选择联系人：" + username, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean checkPermission(String permissionToCheck) {
        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permissionToCheck, "com.lsy.app.help"));
        return permission;
    }

    private static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String result = mobiles.matches(telRegex) ? "是手机号码" : "不是手机号码";
        Util.printLog(mobiles + result);
        return mobiles.matches(telRegex);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Util.printLog("按下返回键");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Util.printLog("按下返回键");
            if(messageFragment != null){
                messageFragment.dismiss();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setBarStyle() {
        Window window = this.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(color);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT>=23) {
            //此处做动态权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 1);
        }
    }
}
