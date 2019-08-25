package com.devyk.pluginlib.base.iml;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devyk.pluginlib.Constants;
import com.devyk.pluginlib.base.IActivity;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:33
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is BaseActivityImp
 * </pre>
 */
public class BaseActivityImp extends AppCompatActivity implements IActivity {

    private final String TAG = getClass().getSimpleName();

    /**
     * 代理 Activity
     */
    protected Activity that;

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        this.that = activity;

        Log.i(TAG, "  onActivityCreated");
        onCreate(bundle);
    }

    /**
     * 通过 View 方式加载
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
        Log.i(TAG, "  setContentView --> view");
        if (that != null) {
            that.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    /**
     * 通过 layoutID 加载
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        Log.i(TAG, "  setContentView --> layoutResID");
        if (that != null) {
            that.setContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
    }

    /**
     * 通过代理 去找布局 ID
     *
     * @param id
     * @param <T>
     * @return
     */
    @Override
    public <T extends View> T findViewById(int id) {
        if (that != null)
            return that.findViewById(id);
        return super.findViewById(id);
    }

    /**
     * 通过 代理去开启 Activity
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        if (that != null) {
            Intent tempIntent = new Intent();
            tempIntent.putExtra(Constants.ACTIVITY_CLASS_NAME, intent.getComponent().getClassName());
            that.startActivity(tempIntent);
        } else
            super.startActivity(intent);
    }


    /**
     * 通过代理去注册广播
     *
     * @param receiver
     * @param filter
     * @return
     */
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if (that != null) {
            return that.registerReceiver(receiver, filter);
        } else
            return super.registerReceiver(receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (that != null) {
            that.unregisterReceiver(receiver);
        } else
            super.unregisterReceiver(receiver);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        if (that != null) {
            that.sendBroadcast(intent);
        } else
            super.sendBroadcast(intent);
    }


    /**
     * 通过代理去开启服务
     *
     * @param service
     * @return
     */
    @Override
    public ComponentName startService(Intent service) {
        if (that != null) {
            Intent tempIntentSer = new Intent();
            tempIntentSer.putExtra(Constants.SERVICE_CLASS_NAME, service.getComponent().getClassName());
            return that.startService(tempIntentSer);
        } else
            return super.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        if (that != null) {
            Intent tempIntentSer = new Intent();
            tempIntentSer.putExtra(Constants.SERVICE_CLASS_NAME, name.getComponent().getClassName());
            return that.stopService(tempIntentSer);
        } else
            return super.stopService(name);
    }

    /**
     * 通过代理去绑定服务
     *
     * @param service
     * @param conn
     * @param flags
     * @return
     */
    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        if (that != null) {
            return that.bindService(service, conn, flags);
        } else
            return super.bindService(service, conn, flags);
    }


    @Override
    public String getPackageName() {
        return that.getPackageName();
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i(TAG, "  onActivityStarted");
        onStart();


    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.i(TAG, "  onActivityResumed");
        onResume();
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.i(TAG, "  onActivityPaused");
        onPause();
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i(TAG, "  onActivityStopped");
        onStop();
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        onSaveInstanceState(bundle);
        Log.i(TAG, "  onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i(TAG, "  onActivityDestroyed");
        onDestroy();

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

    }


    @Override
    protected void onStart() {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }
}
