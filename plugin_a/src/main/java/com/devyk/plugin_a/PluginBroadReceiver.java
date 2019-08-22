package com.devyk.plugin_a;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.devyk.pluginlib.base.iml.BaseBroadReceiverImp;

/**
 * <pre>
 *     author  : devyk on 2019-08-21 18:32
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is PluginBroadReceiver
 * </pre>
 */
public class PluginBroadReceiver extends BaseBroadReceiverImp {

    @Override
    public void attach(Context context) {
        super.attach(context);
        Toast.makeText(context,"绑定广播成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (( "_DevYK").equals(intent.getAction())) {
            Toast.makeText(context,"收到插件中消息 "+intent.getStringExtra("Message"),Toast.LENGTH_SHORT).show();
        }
    }
}
