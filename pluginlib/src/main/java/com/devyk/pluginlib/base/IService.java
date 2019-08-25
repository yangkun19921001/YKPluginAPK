package com.devyk.pluginlib.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:33
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is IService
 * </pre>
 */
public interface IService {
    public void onCreate(Context applicationContext);

    public void onStart(Intent intent, int startId);

    public int onStartCommand(Intent intent, int flags, int startId);

    public void onDestroy();

    public void onConfigurationChanged(Configuration newConfig);

    public void onLowMemory();

    public void onTrimMemory(int level);

    public IBinder onBind(Intent intent);

    public boolean onUnbind(Intent intent);

    public void onRebind(Intent intent);

    public void onTaskRemoved(Intent rootIntent);

}
