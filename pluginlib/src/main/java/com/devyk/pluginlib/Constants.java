package com.devyk.pluginlib;

import android.os.Environment;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:11
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is Constants
 * </pre>
 */
public class Constants {
    /**
     * 需要跳转的 Activity 全路径
     */
    public static final String ACTIVITY_CLASS_NAME = "activityClassName";
    /**
     * 需要启动 Service 全路径
     */
    public static final String SERVICE_CLASS_NAME = "serviceClassName";

    public interface IPluginPath {
        /**
         * 插件路径
         */
        String PlugRootPath = Environment.getExternalStorageDirectory() + "/plugin/";

        /**
         * 插件 APK 路径
         */
        String PlugApkPath = PlugRootPath + "APK/";

        /**
         * 插件 DEX 路径
         */
        String PlugDexPath = PlugRootPath + "DEX/";
    }
}
