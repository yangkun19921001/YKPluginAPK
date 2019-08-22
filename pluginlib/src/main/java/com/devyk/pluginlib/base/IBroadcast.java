package com.devyk.pluginlib.base;

import android.content.Context;
import android.content.Intent;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:32
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is IBroadcast
 * </pre>
 */
public interface IBroadcast {

    /**
     * 通过插件来代理
     * @param context
     */
    public void attach(Context context);

    /**
     * 通过插件来代理接收下发消息
     * @param context
     * @param intent
     */
    public void onReceive(Context context, Intent intent);
}
