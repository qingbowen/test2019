package com.zowoyoo.kintetsu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;
import com.zowoyoo.kintetsu.R;
import com.zowoyoo.kintetsu.config.AppConfig;
import com.zowoyoo.kintetsu.exception.AppException;
import com.zowoyoo.kintetsu.exception.ErrorCode;
import com.zowoyoo.kintetsu.model.CodeDetail;
import com.zowoyoo.kintetsu.service.CodeService;
import com.zowoyoo.kintetsu.tools.AppInfoUtils;
import com.zowoyoo.kintetsu.tools.AppJsonUtils;
import com.zowoyoo.kintetsu.tools.DateUtils;
import com.zowoyoo.kintetsu.tools.QRcodeUtils;
import com.zowoyoo.kintetsu.tools.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    public final static int REQUEST_READ_PHONE_STATE = 1;
    private final int REQUEST_CODE_SCAN = 1;
    private String ANDROID_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注意：高版本需要获取运行时权限,暂不封装
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO_PERMISSION);
        } else {
        }

        //设备id IMEI
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        // ANDROID_ID = "861299030106104";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            ANDROID_ID = tm.getDeviceId();
            Log.i("=============", "---------------" + tm.getDeviceId());
            //Toast.makeText(this,ANDROID_ID,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击扫码打开相机
            case R.id.scan_code:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
            //点击打印小票
            case R.id.print_detail:
                //todo=========
                checkCode("g_jfagvnaslvdasjl");
                break;

        }
    }


    //扫码返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //发送请求查询二维码是否绑定
                checkCode(data.getStringExtra(Constant.CODED_CONTENT));
            }
        }
    }

    /**
     * 验证二维码是是否绑定，
     * 因时间关系及业务简单，
     * 网络请求暂不封装
     */
    private void checkCode(String content) {
        String code = "";
        try {
            //解析扫描的数据
            code = QRcodeUtils.getCode(content);

        } catch (AppException e) {
            ToastUtils.showShortToast(e.getMessage());
            return;
        }

        //拼装url
//        StringBuilder url = new StringBuilder(AppConfig.COMMON_BASE_URL);
//        url.append("g_").append(code).append("_").append(ANDROID_ID).append("_")
//                .append(AppInfoUtils.md5("code=" + code + "&sn=" + ANDROID_ID + "&date=" + DateUtils.getToday() + "&key=" + AppConfig.MD5_KEY));


        //todo===start
        String url = AppConfig.TEST_URL;
        StringBuilder params = new StringBuilder();
        params.append(code).append("_").append(ANDROID_ID).append("_")
                .append(AppInfoUtils.md5("code=" + code + "&sn=" + ANDROID_ID + "&date=" + DateUtils.getToday() + "&key=" + AppConfig.MD5_KEY));
        //todo===end

        //发请求
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        CodeService codeService = retrofit.create(CodeService.class);
        //todo===start
        Call<String> personResCall = codeService.checkCodeTest(params.toString());
        //todo===end
        personResCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    // 处理请求
                    CodeDetail codeDetail = AppJsonUtils.fromJson(response.body().trim(), CodeDetail.class);
                } catch (AppException e) {
                    ToastUtils.showShortToast(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ToastUtils.showShortToast(ErrorCode.UNKNOWN_ERROR.toString());
            }
        });


    }


}
