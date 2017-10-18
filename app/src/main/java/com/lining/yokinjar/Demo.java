package com.lining.yokinjar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lining.yokinlibrary.utils.ImageUtils;

/**
 * 作者：yokin.li on 2017/10/18 14:06
 * 邮箱：86558543@qq.com
 */

public class Demo extends AppCompatActivity {
    private ImageView iv;

    private String testUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508317011237&di=5f319d058ff38b6e7be5899432d43ac6&imgtype=0&src=http%3A%2F%2Fq.115.com%2Fimgload%3Fr%3DD1C74328B3D4694531D3A0D6021E94D7D0537962%26u%3DlTr6HK%26s%3DE-pNmSkCw3AXx3bMMnGAEA%26e%3D5%26st%3D0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demo);

        iv= (ImageView) findViewById(R.id.iv);
        ImageUtils.loadLocalCicleImage(iv,testUrl,R.mipmap.ic_launcher);
    }
}
