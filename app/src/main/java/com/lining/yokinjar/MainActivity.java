package com.lining.yokinjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lining.yokinlibrary.utils.ActivityCache;
import com.lining.yokinlibrary.utils.CL;

public class MainActivity extends AppCompatActivity {
    private String TAG=MainActivity.class.getSimpleName();
    TextView click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCache.addActivity(MainActivity.this);

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
                startActivity(new Intent(MainActivity.this,Demo.class));

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CL.e(MainActivity.class.getSimpleName()+" finish");
    }
}
