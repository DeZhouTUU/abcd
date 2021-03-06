package com.dezhoutuu.mytuu.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dezhoutuu.mytuu.Application.BaseActivity;
import com.dezhoutuu.mytuu.Application.MainService;
import com.dezhoutuu.mytuu.R;
import com.dezhoutuu.mytuu.Utils.TUUToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_use_name)
    EditText etUseName;
    @BindView(R.id.et_passward)
    EditText etPassward;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private List<String> mPermissionList = new ArrayList<>();
    //            Manifest.permission.WRITE_EXTERNAL_STORAGE,所需的全部权限
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SYSTEM_ALERT_WINDOW
    };
    private static final int MY_PERMISSIONS_REQUEST = 0;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        getPermissions();
    }

    @OnClick(R.id.btn_login)
    public void login(){
        if (Settings.canDrawOverlays(MainActivity.this))
        {
            Intent intent = new Intent(MainActivity.this,MainService.class);
            Toast.makeText(MainActivity.this,"已开启Toucher",Toast.LENGTH_SHORT).show();
            startService(intent);
            finish();
        }else
        {
            //若没有权限，提示获取.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            Toast.makeText(MainActivity.this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
    }

    private void getPermissions(){
        mPermissionList.clear();
        for (int i = 0; i < PERMISSIONS.length; i++) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(PERMISSIONS[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(MainActivity.this,"已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, MY_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST){
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[i]);
                    if (showRequestPermission) {
                        new TUUToast(MainActivity.this,"权限未申请");
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
