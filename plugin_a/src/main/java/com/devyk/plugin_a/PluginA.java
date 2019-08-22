package com.devyk.plugin_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.devyk.pluginlib.base.iml.BaseActivityImp;

public class PluginA extends BaseActivityImp {

    private PluginBroadReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(that, PluginA_2.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
        findViewById(R.id.sendmessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null)
            unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * 动态注册广播
     */
    public void register() {
        //动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("_DevYK");
        receiver = new PluginBroadReceiver();
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 发送消息
     */
    public void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("_DevYK");
        intent.putExtra("Message", getClass().getSimpleName());
        sendBroadcast(intent);
    }
}
