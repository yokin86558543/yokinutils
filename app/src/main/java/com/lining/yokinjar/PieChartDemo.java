package com.lining.yokinjar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/15.
 */

public class PieChartDemo extends AppCompatActivity {

    PieChart mChart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartdemo);

        mChart= (PieChart) findViewById(R.id.piechart);
        initChart();
    }

    private void initChart(){
        //设置显示成比例
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);
        //设置半圆
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        //半径
        mChart.setHoleRadius(58f);
        // 半透明圈
        mChart.setTransparentCircleRadius(54f);
        //饼状图中间可以添加文字
        mChart.setDrawCenterText(false);
        //初始旋转角度
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        //可以手动旋转
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        setData(3, 100);
        //设置动画
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setEnabled(false);//设置禁用比例块

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    "",
                    getResources().getDrawable(R.mipmap.ic_launcher)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        //设置颜色
        int c=Color.rgb(255, 216, 37);
        colors.add(c);
        c=Color.rgb(249, 119, 95);
        colors.add(c);
        c=Color.rgb(97, 204, 176);
        colors.add(c);
        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //设置显示字体大小
        //data.setValueTextSize(11f);
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }
}
