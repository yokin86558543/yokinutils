package com.lining.yokinjar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.lining.yokinlibrary.utils.ActivityCache;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class Test extends AppCompatActivity {

    LineChart mlinechart;
    ArrayList<String> courseList;

    RatioImageView imageview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityCache.finishSingleActivityByClass(MainActivity.class);
        setContentView(R.layout.activity_test);

        mlinechart= (LineChart) findViewById(R.id.linechart);
        ChartUtils.initChart(mlinechart);
        ChartUtils.notifyDataSetChanged(mlinechart, getData(), ChartUtils.weekValue);

        imageview= (RatioImageView) findViewById(R.id.imageview);
        String url="http://imgcl.oukzj.com/store/goods/0/0_05572491273270574_360.jpg";
        //Glide.with(Test.this).load(url).into(imageview);
        Picasso.with(Test.this).load(url).into(imageview);
    }
    private List<Entry> getData() {
        List<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 15));
        values.add(new Entry(1, 15));
        values.add(new Entry(2, 15));
        values.add(new Entry(3, 20));
        values.add(new Entry(4, 25));
        values.add(new Entry(5, 20));
        values.add(new Entry(6, 20));
        values.add(new Entry(7, 15));
        values.add(new Entry(8, 20));
        values.add(new Entry(9, 25));
        values.add(new Entry(10, 20));
        values.add(new Entry(11, 20));
        values.add(new Entry(12, 15));
        values.add(new Entry(13, 20));
        return values;
    }

    class myEntry extends BaseEntry implements Serializable{
        String XText;
        String YText;

        public String getXText() {
            return XText;
        }

        public void setXText(String XText) {
            this.XText = XText;
        }

        public String getYText() {
            return YText;
        }

        public void setYText(String YText) {
            this.YText = YText;
        }
    }
}
