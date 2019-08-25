package com.devyk.android_pluginapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devyk.pluginlib.Constants;
import com.devyk.pluginlib.PluginManager;
import com.devyk.pluginlib.proxy.ProxyActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadplugin(View view) {
        File file = new File(Constants.IPluginPath.PlugApkPath);
        if (!file.exists())
            file.mkdirs();
        boolean isSuccess = PluginManager.getInstance().loadPlugin(this, Constants.IPluginPath.PlugApkPath + "plugin.apk");
        Toast.makeText(getApplicationContext(), isSuccess == true ? "load_success" : "load_error", Toast.LENGTH_SHORT).show();
    }

    //跳转插件
    public void toPluginA(View view) {
        if (PluginManager.getInstance().getPluginPackageInfo() != null) {
            if (PluginManager.getInstance().getPluginPackageInfo().activities.length > 0) {
                String className = PluginManager.getInstance().getPluginPackageInfo().activities[0].name;
                Intent toPa = new Intent(this, ProxyActivity.class);
                toPa.putExtra(Constants.ACTIVITY_CLASS_NAME, className);
                startActivity(toPa);
                return;
            }
        }

        Toast.makeText(getApplicationContext(),"确定是否已加载插件？",Toast.LENGTH_LONG).show();
    }

}
