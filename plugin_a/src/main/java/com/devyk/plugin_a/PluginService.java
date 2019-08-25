package com.devyk.plugin_a;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.devyk.pluginlib.base.iml.BaseServiceImp;

/**
 * <pre>
 *     author  : devyk on 2019-08-23 11:50
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is PluginService
 * </pre>
 */
public class PluginService extends BaseServiceImp {
    @Override
    public void onCreate(Context applicationContext) {
        super.onCreate(applicationContext);
        Toast.makeText(mContext,"Service onCreate-启动成功："+getClass().getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(mContext,"Service onStartCommand-启动成功："+getClass().getName(),Toast.LENGTH_SHORT).show();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(mContext,"Service onDestroy："+getClass().getName(),Toast.LENGTH_SHORT).show();
    }
}
