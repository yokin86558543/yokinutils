package com.lining.yokinjar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lining.yokinlibrary.utils.CL;
import com.othershe.calendarview.CalendarView;
import com.othershe.calendarview.DateBean;
import com.othershe.calendarview.WeekView;
import com.othershe.calendarview.listener.OnMonthItemChooseListener;
import com.othershe.calendarview.listener.OnMonthItemClickListener;

/**
 * 作者：yokin.li on 2017/10/17 14:53
 * 邮箱：86558543@qq.com
 */

public class CalendarDemo extends AppCompatActivity {
    TextView riqi_tv;
    WeekView weekview;
    CalendarView calendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_calendar);
        riqi_tv= (TextView) findViewById(R.id.riqi_tv);
        weekview= (WeekView) findViewById(R.id.weekview);
        calendar= (CalendarView) findViewById(R.id.calendar);
        calendar.init();

        DateBean date = calendar.getDateInit();
        riqi_tv.setText(date.getSolar()[0]+"-"+date.getSolar()[1]+"-"+date.getSolar()[2]);
        //多选回调
        calendar.setOnMonthItemChooseListener(new OnMonthItemChooseListener() {
            @Override
            public void onMonthItemChoose(View view, DateBean date, boolean flag) {
                String d = date.getSolar()[0] + "." + date.getSolar()[1] + "." + date.getSolar()[2] + ".";
                if (flag) {//选中
                    CL.e("选中：" + d + "\n");
                } else {//取消选中
                    CL.e("取消：" + d + "\n");
                }
                //chooseDate.setText(sb.toString());
            }
        });

        //日期点击回调
        calendar.setOnItemClickListener(new OnMonthItemClickListener() {
            @Override
            public void onMonthItemClick(View view, DateBean date) {
                String d = date.getSolar()[0] + "." + date.getSolar()[1] + "." + date.getSolar()[2] + ".";
                CL.e("点击：" + d + "\n");
            }
        });
    }
}
