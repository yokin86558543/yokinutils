package com.lining.yokinlibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 作者：yokin.li on 2017/10/19 11:10
 * 邮箱：86558543@qq.com
 * 文件工具类
 */

public class FileUtils {

    public FileUtils() {
    }

    /**
     * 将文件放入到一个文件list中
     * @param file
     * @return
     */
    public static List<File> AddFileToFiles(File file) {
        Object files = null;
        if(file != null && file.exists() && file.canRead()) {
            for(int i = 0; i < ((List)files).size(); ++i) {
                if(file.getPath().equalsIgnoreCase(((File)((List)files).get(i)).getPath())) {
                    return (List)files;
                }
            }

            ((List)files).add(file);
            return (List)files;
        } else {
            return (List)files;
        }
    }

    /**
     * 获取手机SD卡路径 带/
     * @return
     */
    public static String getSDPath() {
        boolean sdCardExist = isSDExits();
        if(sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString() + "/";
        } else {
            return null;
        }
    }

    /**
     * 判断是否存在SD卡
     * @return
     */
    public static boolean isSDExits() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /**
     * 检查SD卡是否放得下
     * @param fileLength
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean checkSDStorageAvailable(long fileLength) {
        String state = Environment.getExternalStorageState();
        if("mounted".equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long blockSize = sf.getBlockSizeLong();
            long availCount = sf.getAvailableBlocksLong();
            if(availCount * blockSize >= fileLength) {
                return true;
            }
        }

        return false;
    }

    /**
     * 创建文件
     * @param file
     */
    public static void createDipPath(String file) {
        String parentFile = file.substring(0, file.lastIndexOf("/"));
        File file1 = new File(file);
        File parent = new File(parentFile);
        if(!file1.exists()) {
            parent.mkdirs();

            try {
                file1.createNewFile();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

    }

    public static String getSystemDCIMPath(Context context) {
        String[] projection = new String[]{"_id", "_id", "image_id", "_data"};
        Cursor cur = context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, (String)null, (String[])null, (String)null);
        if(cur != null && cur.moveToFirst()) {
            int dataColumn = cur.getColumnIndex("_data");
            String image_path = cur.getString(dataColumn);
            if(!TextUtils.isEmpty(image_path)) {
                image_path = image_path.toLowerCase();
                String[] paths = image_path.split(".thumbnails");
                if(paths != null && paths.length > 0) {
                    String dcimPath = paths[0];
                    if(!TextUtils.isEmpty(dcimPath) && !dcimPath.endsWith(File.separator)) {
                        dcimPath = dcimPath + File.separator;
                    }

                    return dcimPath;
                }
            }
        }

        if(cur != null) {
            cur.close();
        }

        return null;
    }
}
