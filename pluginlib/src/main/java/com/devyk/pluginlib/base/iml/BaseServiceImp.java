package com.devyk.pluginlib.base.iml;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.devyk.pluginlib.base.IService;

/**
 * <pre>
 *     author  : devyk on 2019-08-22 23:48
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is BaseServiceImp
 * </pre>
 */
public class BaseServiceImp extends Service implements IService {


    public Context mContext;

    @Override
    public void onCreate(Context applicationContext) {
        this.mContext = applicationContext.getApplicationContext();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
