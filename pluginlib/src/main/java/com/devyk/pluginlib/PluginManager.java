package com.devyk.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * <pre>
 *     author  : devyk on 2019-08-21 10:56
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is PluginManager 插件管理类
 * </pre>
 */
public class PluginManager {


    private final String TAG = this.getClass().getSimpleName();
    private static PluginManager instance;

    /**
     * 负责加载 APK
     */
    private DexClassLoader mDexClassLoader;

    /**
     * 得到未安装 APK 里面的资源
     */
    private Resources mResources;

    /**
     * 插件 APK 路径
     */
    private String apkFilePath;

    private Context mContext;
    private PackageManager packageManager;
    private File mDexPath;
    private PackageInfo pluginPackageInfo;

    public static synchronized PluginManager getInstance() {
        if (instance == null)
            instance = new PluginManager();
        return instance;
    }

    /**
     * 加载插件 APK
     */
    public boolean loadPlugin(Context context, String filePath) {
        if (context == null || filePath == null || filePath.isEmpty())
            throw new NullPointerException("context or filePath is null ?");
        this.mContext = context.getApplicationContext();
        this.apkFilePath = filePath;
        //拿到 包管理
        packageManager = mContext.getPackageManager();

        if (getPluginPackageInfo(apkFilePath) == null) {
            return false;
        }
        //从包里获取 Activity
        pluginPackageInfo = getPluginPackageInfo(apkFilePath);

        //存放 DEX 路径
        mDexPath = new File(Constants.IPluginPath.PlugDexPath);
        if (mDexPath.exists())
            mDexPath.delete();
        else
            mDexPath.mkdirs();

        //通过 DexClassLoader 加载 apk 并通过 native 层解析 apk 输出 dex
        //第二个参数可以为 null 底层没有做处理
        if (getPluginClassLoader(apkFilePath, mDexPath.getAbsolutePath()) == null || getPluginResources(filePath) == null)
            return false;
        this.mDexClassLoader = getPluginClassLoader(apkFilePath, mDexPath.getAbsolutePath());
        this.mResources = getPluginResources(filePath);
        return true;

    }


    /**
     * @param apkPath
     * @return 得到对应插件 APK 的 Resource 对象
     */
    private Resources getPluginResources(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            //反射调用方法addAssetPath(String path)
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            //将未安装的Apk文件的添加进AssetManager中,第二个参数是apk的路径
            addAssetPath.invoke(assetManager, apkPath);
            Resources superRes = mContext.getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
            return mResources;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * @return 得到对应插件 APK 的 Resource 对象
     */
    public Resources getPluginResources() {
        return getPluginResources(apkFilePath);
    }

    /**
     * 得到对应插件 APK 中的 加载器
     *
     * @param apkFile
     * @param dexPath
     * @return
     */
    public DexClassLoader getPluginClassLoader(String apkFile, String dexPath) {
        return new DexClassLoader(apkFile, dexPath, null, mContext.getClassLoader());
    }


    /**
     * 得到对应插件 APK 中的 加载器
     *
     * @return
     */
    public DexClassLoader getPluginClassLoader() {
        return getPluginClassLoader(apkFilePath, mDexPath.getAbsolutePath());
    }


    /**
     * 得到插件 APK 中 包信息
     */
    public PackageInfo getPluginPackageInfo(String apkFilePath) {
        if (packageManager != null)
            return packageManager.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES);
    return null;
    }

    /**
     * 得到插件 APK 中 包信息
     */
    public PackageInfo getPluginPackageInfo() {
        return getPluginPackageInfo(apkFilePath);
    }


}
