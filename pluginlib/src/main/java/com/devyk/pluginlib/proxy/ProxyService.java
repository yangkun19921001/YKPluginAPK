package com.devyk.pluginlib.proxy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.devyk.pluginlib.Constants;
import com.devyk.pluginlib.PluginManager;
import com.devyk.pluginlib.base.IService;

import java.lang.reflect.Constructor;

/**
 * <pre>
 *     author  : devyk on 2019-08-22 23:45
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is ProxyService
 * </pre>
 */
public class ProxyService extends Service {

    private IService iService;

    @Override
    public IBinder onBind(Intent intent) {
        return iService.onBind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (iService == null)
            init(intent);
        return iService.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
       iService.onStart(intent,startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        iService.onUnbind(intent);
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        iService.onDestroy();
    }


    //初始化
    public void init(Intent proIntent) {
        //拿到需要启动服务的全类名
        String serviceClassName = getServiceClassName(proIntent);
        try {
            Class<?> pluginService = PluginManager.getInstance().getPluginClassLoader().loadClass(serviceClassName);
            Constructor<?> constructor = pluginService.getConstructor(new Class[]{});
            iService = (IService) constructor.newInstance(new Object[]{});
            iService.onCreate(getApplicationContext());
        } catch (Exception e) {
            //加载 class
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getPluginClassLoader();
    }

    public String getServiceClassName(Intent intent) {
        return intent.getStringExtra(Constants.SERVICE_CLASS_NAME);
    }
}
