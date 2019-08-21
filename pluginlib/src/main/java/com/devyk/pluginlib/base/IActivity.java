package com.devyk.pluginlib.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *     author  : devyk on 2019-08-20 22:32
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is IActivity
 * </pre>
 */
public interface IActivity {


    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle);

    public void onActivityStarted(@NonNull Activity activity);

    public void onActivityResumed(@NonNull Activity activity);

    public void onActivityPaused(@NonNull Activity activity);

    public void onActivityStopped(@NonNull Activity activity);

    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle);

    public void onActivityDestroyed(@NonNull Activity activity);
    public void onBackPressed();
}
