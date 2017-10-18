package com.lining.yokinjar;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

/**
 * 作者：yokin.li on 2017/9/25 09:50
 * 邮箱：86558543@qq.com
 */

public class UserDemo extends AppCompatActivity {
    private String TAG=UserDemo.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UsageStatsManager m=(UsageStatsManager)this.getSystemService(Context.USAGE_STATS_SERVICE);
        long ts=System.currentTimeMillis();
        long time =ts-2000;
        List<UsageStats> list=m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time, ts);
        for (UsageStats usageStats:list){
            Log.e(TAG,"包名:"+usageStats.getPackageName());
            Log.e(TAG,"用时:"+usageStats.getTotalTimeInForeground());
        }

    }
}
