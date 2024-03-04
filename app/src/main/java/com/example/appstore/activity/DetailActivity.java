package com.example.appstore.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.appstore.R;
import com.example.appstore.appinfo.AppData;
import com.example.appstore.retrofit.RetrofitUtil;
import com.example.appstore.service.ITvService;
import com.example.appstore.util.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    Button downloadBtn;
    TextView appName;
    TextView appInfo;
    TextView appDetail;
    TextView appLabel;
    ImageView appIcon;
    private String versionName;
    private String apkName;
    private String downloadUrl;
    private String packagename;
    private  AppData appData;
    private String installPath ;
    private Timer timer = new Timer();
    private Long curDownLoadLength = 0L;
    private int curState = 0;
    private MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        handler = new MyHandler();
        initView();
        if (Build.VERSION.SDK_INT > 29){
            installPath = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();
        }else{
            installPath =Environment.getExternalStorageDirectory().getPath();
        }

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (curState){
                    case 0: //DownLoad
                        handler.sendEmptyMessage(1);
                        download();
                        break;
                    case 1: //
                        Toast.makeText(DetailActivity.this, "下载中", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: //Install
                        File file = new File(installPath + "/" + apkName);
                        installingAPK(file);
                        break;
                    case 3: //Open
                        startNewApp(apkName);
                        break;
                }

            }
        });

    }
    private void startNewApp(String pkgName){
        if(pkgName ==null){
            LogUtil.d(TAG, "startNewApp: pkgName ==null");
            return;
        }
        Intent intent = getPackageManager().getLaunchIntentForPackage(packagename);
        try {
            startActivity(intent);
        }catch (SecurityException e){
            LogUtil.e(TAG, "startNewApp: error");
            Toast.makeText(this, "该应用权限限制无法被三方应用启动", Toast.LENGTH_SHORT).show();
        }

    }
    private void initView() {
        Intent intent = getIntent();
        appData= (AppData) intent.getSerializableExtra("appid");

        downloadBtn = findViewById(R.id.download_button);
        appName = findViewById(R.id.app_name);
        appInfo = findViewById(R.id.app_info);
        appIcon = findViewById(R.id.app_icon);
        appDetail = findViewById(R.id.app_detail);
        appLabel = findViewById(R.id.app_labels);
        Glide.with(this).load(appData.geticon()).into(appIcon);
        long time = (long)appData.getCreate_at() * 1000;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date(time));
        appName.setText(appData.getName());
        appDetail.setText(appData.getDetail());
        packagename = appData.getpackage_name();
        appInfo.setText(appData.getSize()+" | 版本："+appData.getVersion_name()+" | 更新时间："+ date);
        //checkButtonStatus();
        versionName = appData.getName()+".apk";
        apkName = appData.getpackage_id()+".apk";


    }

    @Override
    protected void onResume() {
        checkStatus();
        Log.d(TAG, "onResume: "+installPath);
        super.onResume();
        downloadBtn.requestFocus();
    }

    public void checkStatus(){
        File file = new File(installPath+ "/" + apkName);
        if (isAppInstalled(packagename)){
            handler.sendEmptyMessage(3);
            //delete local apk file
            File file1 = new File(installPath + "/" +apkName);
            if(file1.exists()){
                file1.delete();
                LogUtil.d(TAG,"app installed and delete local apk file");
            }
        }else if( file.exists() && !isAppInstalled(packagename)){
            handler.sendEmptyMessage(2);
        }else{
            handler.sendEmptyMessage(0);
        }

    }

    @Override
    public void onBackPressed() {
        if(curState == 1){
            Toast.makeText(this, "下载中", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }

    private void download() {
        ITvService iTvService = RetrofitUtil.getInstance().getRetrofit().create(ITvService.class);
        retrofit2.Call<ResponseBody> call = iTvService.download(appData.getpackage_id());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        File file = new File(installPath + "/" +apkName);
                        //保存到本地
                        writeFile2Disk(response.body(), file);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e(TAG,"download request fail");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailActivity.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void writeFile2Disk(ResponseBody response, File file) {
        OutputStream os = null;
        InputStream is = response.byteStream(); //获取下载输入流
        Long totalLength = response.contentLength();

        try {
            os = new FileOutputStream(file); //输出流
            int len;
            byte[] buff = new byte[4096];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                curDownLoadLength += len;
                LogUtil.d(TAG, "当前进度: " + curDownLoadLength);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        curState = 1;
                        String curProcess =  String.format("%.2f",curDownLoadLength.doubleValue()/totalLength.doubleValue() *100);
                        downloadBtn.setText(  curProcess+"%" );
                    }
                });
            }
            installingAPK(file);
            handler.sendEmptyMessage(2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close(); //关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close(); //关闭输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void installingAPK(final File file) {
        LogUtil.d(TAG, "installingAPK: "+file.getAbsolutePath());
        if (!file.exists()){
            LogUtil.e(TAG, "installingAPK: file not exist");
            return;
        }
        handler.sendEmptyMessage(2);
        Intent intent = new Intent();
        //安卓7.0以上需要在在Manifest.xml里的application里，设置provider路径
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(this, "com.example.appstore.AppFileProvider", new File(file.getPath()));
            LogUtil.d(TAG, "installingAPK: " + contentUri.getPath() + ", getEncodePath = " + contentUri.getEncodedPath());
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setAction(Intent.ACTION_VIEW);
        }
        getApplicationContext().startActivity(intent);


    }
    private boolean isAppInstalled(String pkgName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        // 桌面启动属性
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getPackageManager();
        // 使用 queryIntentActivities 获取应用名称和包名
        List<ResolveInfo> mResolveInfos = packageManager.queryIntentActivities(intent,PackageManager.MATCH_ALL);
        if (mResolveInfos == null || mResolveInfos.isEmpty()) {
           return false;
        } for (ResolveInfo info: mResolveInfos) {
            LogUtil.d(TAG,info.activityInfo.packageName);
            if(pkgName.equals(info.activityInfo.packageName)){
                LogUtil.d(TAG,"package matched");
                return true;
            }
        }
        return false;
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    curState = 0;
                    downloadBtn.setText("下载");
                    break;
                case 1:
                    curState = 1;
                    downloadBtn.setText("下载中");
                    break;
                case 2:
                    curState = 2;
                    downloadBtn.setText("安装");
                    break;
                case 3:
                    curState = 3;
                    downloadBtn.setText("打开");
                    break;
            }
        }
    }

}