package com.devyk.pluginlib.proxy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devyk.pluginlib.Constants;
import com.devyk.pluginlib.PluginManager;
import com.devyk.pluginlib.base.IActivity;

import java.lang.reflect.Constructor;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:18
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is ProxyActivity 代理插件加载 Activity
 * </pre>
 */
public class ProxyActivity extends AppCompatActivity {

    /**
     * 需要加载插件的全类名
     */
    protected String activityClassName;

    private String TAG = this.getClass().getSimpleName();
    private IActivity iActivity;
    private ProxyBroadcast receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityClassName = getLoadClassName();

        //拿到加载插件的的全类名 通过反射实例化
        try {
            Class<?> pluginClassName = getClassLoader().loadClass(activityClassName);
            //拿到构造函数
            Constructor<?> constructor = pluginClassName.getConstructor(new Class[]{});
            //实例化 拿到插件 UI
            Object pluginObj = constructor.newInstance(new Object[]{});
            if (pluginObj != null) {
                iActivity = (IActivity) pluginObj;
                iActivity.onActivityCreated(this, savedInstanceState);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private String getLoadClassName() {
        return getIntent().getStringExtra(Constants.ACTIVITY_CLASS_NAME);
    }

    private String getLoadClassName(Intent intent) {
        return intent.getStringExtra(Constants.ACTIVITY_CLASS_NAME);
    }

    private String getLoadServiceClassName(Intent intent) {
        return intent.getStringExtra(Constants.SERVICE_CLASS_NAME);
    }

    /**
     * 这里的 startActivity 是插件调用的
     */
    @Override
    public void startActivity(Intent intent) {
        String className = getLoadClassName(intent);
        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra(Constants.ACTIVITY_CLASS_NAME, className);
        super.startActivity(proxyIntent);
    }

    /**
     * 加载插件中 启动服务
     * @param service
     * @return
     */
    @Override
    public ComponentName startService(Intent service) {
        String className = getLoadServiceClassName(service);
        Intent intent = new Intent(this,ProxyService.class);
        intent.putExtra(Constants.SERVICE_CLASS_NAME,className);
        return super.startService(intent);
    }

    @Override
    public boolean stopService(Intent name) {
        String className = getLoadServiceClassName(name);
        Intent intent = new Intent(this,ProxyService.class);
        intent.putExtra(Constants.SERVICE_CLASS_NAME,className);
        return super.stopService(intent);
    }

    /**
     * 获得插件中的 ClassLoader
     *
     * @return
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getPluginClassLoader();
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        IntentFilter proxyIntentFilter = new IntentFilter();
        for (int i = 0; i < filter.countActions(); i++) {
            //内部是一个数组
            proxyIntentFilter.addAction(filter.getAction(i));
        }
        //交给代理广播去注册
        this.receiver = new ProxyBroadcast(receiver.getClass().getName(), this);
        return super.registerReceiver(this.receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receive) {
        Toast.makeText(getApplicationContext(), "取消广播注册成功", Toast.LENGTH_SHORT).show();
        if (receiver != null)
            super.unregisterReceiver(receiver);
    }

    /**
     * 获取插件中的资源
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iActivity.onActivityStarted(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        iActivity.onActivityResumed(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        iActivity.onActivityPaused(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iActivity.onActivityDestroyed(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        iActivity.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        iActivity.onActivitySaveInstanceState(this, outState);
    }
}
