package com.testgame.persistentcontent;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumApplication;
/**
 * Created by Daniel Bogdanov on 09/20/21.
 */

public class PersistentContentApp extends LeanplumApplication
{
    @Override
    public void onCreate() {
        super.onCreate();
        Leanplum.setApplicationContext(this);
        Leanplum.setLogLevel(4);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        Leanplum.setAppIdForDevelopmentMode("app_8fM8YyCDJ8Pdut5hOzT8QX46WU7Ys5xjDiotdjOqgbE", "dev_tGxYGe7FztMpyaQlWm4v4U1cC7E98H9Gy0RilLPzWlQ");
        Leanplum.start(this);
        Leanplum.setUserId("Dany");
    }

}

