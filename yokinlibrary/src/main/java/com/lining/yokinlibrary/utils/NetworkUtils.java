package com.lining.yokinlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;

/**
 * 作者：yokin.li on 2017/10/19 11:39
 * 邮箱：86558543@qq.com
 * 网络链接工具
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    public NetworkUtils() {
    }

    public static ConnectivityManager getConnManager(Context context) {
        return (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if(nets != null) {
            NetworkInfo[] var2 = nets;
            int var3 = nets.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                NetworkInfo net = var2[var4];
                if(net.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static NetworkUtils.NetType getConnectedType(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        if(net != null) {
            switch(net.getType()) {
                case 0:
                    return NetworkUtils.NetType.Mobile;
                case 1:
                    return NetworkUtils.NetType.Wifi;
                default:
                    return NetworkUtils.NetType.Other;
            }
        } else {
            return NetworkUtils.NetType.None;
        }
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 1 && net.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 0 && net.isConnected();
    }

    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) && isWifiEnabled(context) || isMobileAvailable(context) && isMobileEnabled(context);
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if(nets != null) {
            NetworkInfo[] var2 = nets;
            int var3 = nets.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                NetworkInfo net = var2[var4];
                if(net.getType() == 1) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isWifiEnabled(Context context) {
        try {
            WifiManager e = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            return e.isWifiEnabled();
        } catch (Exception var2) {
            var2.printStackTrace();
            return true;
        }
    }

    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if(nets != null) {
            NetworkInfo[] var2 = nets;
            int var3 = nets.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                NetworkInfo net = var2[var4];
                if(net.getType() == 0) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isMobileEnabled(Context context) {
        try {
            Method e = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            e.setAccessible(true);
            return ((Boolean)e.invoke(getConnManager(context), new Object[0])).booleanValue();
        } catch (Exception var2) {
            var2.printStackTrace();
            return true;
        }
    }

    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            NetworkInfo in = connectivity.getActiveNetworkInfo();
            CL.e(TAG, "-------------$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$-------------");
            CL.e(TAG, "getActiveNetworkInfo: " + in);
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if(info != null) {
                for(int i = 0; i < info.length; ++i) {
                    CL.e(TAG, "NetweorkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    CL.e(TAG, "NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    CL.e(TAG, "NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    CL.e(TAG, "NetworkInfo[" + i + "]: " + info[i]);
                }

                CL.e(TAG, "\n");
            } else {
                CL.e(TAG, "getAllNetworkInfo is null");
            }
        }

        return false;
    }

    public static enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        public int value;

        private NetType(int value) {
            this.value = value;
        }
    }
}
