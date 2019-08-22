package com.devyk.pluginlib.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.devyk.pluginlib.PluginManager;
import com.devyk.pluginlib.base.IBroadcast;

import java.lang.reflect.Constructor;

/**
 * <pre>
 *     author  : devyk on 2019-08-21 18:05
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is ProxyBroadcast
 * </pre>
 */
public class ProxyBroadcast extends BroadcastReceiver {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 需要加载插件全类名的 广播
     */
    private String broadcastClassName;

    public ProxyBroadcast() {
    }

    /**
     * 交于代理广播中处理
     */
    private IBroadcast iBroadcast;

    public ProxyBroadcast(String broadcastClassName, Context context) {
        this.broadcastClassName = broadcastClassName;
        this.iBroadcast = iBroadcast;

        //通过加载插件的 DexClassLoader loadClass
        try {
            Class<?> pluginBroadcastClassName = PluginManager.getInstance().getPluginClassLoader().loadClass(broadcastClassName);
            Constructor<?> constructor = pluginBroadcastClassName.getConstructor(new Class[]{});
            iBroadcast = (IBroadcast) constructor.newInstance(new Object[]{});
            iBroadcast.attach(context);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        iBroadcast.onReceive(context, intent);
    }
}
