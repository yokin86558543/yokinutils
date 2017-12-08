package com.lining.yokinjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.lining.yokinlibrary.utils.ActivityCache;
import com.lining.yokinlibrary.utils.CL;
import com.lining.yokinlibrary.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    private String TAG=MainActivity.class.getSimpleName();
    TextView click;
    SwitchCompat switchcompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCache.addActivity(MainActivity.this);

        switchcompat= (SwitchCompat) findViewById(R.id.switchcompat);

        click= (TextView) findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(MainActivity.this,Test.class));
                //startActivity(new Intent(MainActivity.this,PieChartDemo.class));
                //startActivity(new Intent(MainActivity.this,CooLDemo.class));
                //startActivity(new Intent(MainActivity.this,UserDemo.class));
                //startActivity(new Intent(MainActivity.this,ErJiDemo.class));
                //startActivity(new Intent(MainActivity.this,CalendarDemo.class));
                //startActivity(new Intent(MainActivity.this,Demo.class)); 1+20*4

            }
        });


        switchcompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this,"点击了=="+isChecked,Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CL.e(MainActivity.class.getSimpleName()+" finish");
    }
}
