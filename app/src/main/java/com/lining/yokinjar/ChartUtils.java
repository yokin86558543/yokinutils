package com.lining.yokinjar;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.List;

/**
 * 图表工具
 * Created by yangle on 2016/11/29.
 */
public class ChartUtils {

    public static int dayValue = 0;
    public static int weekValue = 1;
    public static int monthValue = 2;

    //xy线跟字体的颜色
    public static int xyLineAndTextColor=Color.parseColor("#5b3829");

    /**
     * 初始化图表
     *
     * @param chart 原始图表
     * @return 初始化后的图表
     */
    public static LineChart initChart(LineChart chart) {
        // 不显示数据描述
        chart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        chart.setNoDataText("暂无数据");
        // 显示表格颜色
        chart.setDrawGridBackground(true);
        // 不可以缩放
        chart.setScaleEnabled(false);
        // 不显示y轴右边的值
        chart.getAxisRight().setEnabled(false);
        // 不显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        // 向左偏移15dp，抵消y轴向右偏移的30dp
        chart.setExtraLeftOffset(-15);

        XAxis xAxis = chart.getXAxis();
        // 显示x轴
        xAxis.setDrawAxisLine(true);
        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(xyLineAndTextColor);
        //x轴字体的大小
        xAxis.setTextSize(12);
        xAxis.setGridColor(xyLineAndTextColor);
        // 设置x轴数据偏移量
        xAxis.setYOffset(3);

        YAxis yAxis = chart.getAxisLeft();
        // 显示y轴
        yAxis.setDrawAxisLine(true);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
        yAxis.setDrawGridLines(true);
        yAxis.setTextColor(xyLineAndTextColor);
        yAxis.setGridColor(xyLineAndTextColor);
        yAxis.setTextSize(12);
        // 设置y轴数据偏移量
        yAxis.setXOffset(30);
        yAxis.setYOffset(-3);
        yAxis.setAxisMinimum(0);

//        Matrix matrix = new Matrix();
//        // x轴缩放1.5倍
//        matrix.postScale(1.5f, 1f);
//        // 在图表动画显示之前进行缩放
//        chart.getViewPortHandler().refresh(matrix, chart, false);
        // x轴执行动画
        //chart.animateX(2000);
        chart.invalidate();
        return chart;
    }

    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public static void setChartData(LineChart chart, List<Entry> values) {
        LineDataSet lineDataSet;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "");
            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#ff6930"));
            // 设置平滑曲线
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            //设置坐标点的小圆点颜色
            lineDataSet.setCircleColor(Color.RED);
            //设置折现
            lineDataSet.setMode(LineDataSet.Mode.LINEAR);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            // 不显示定位线
            lineDataSet.setHighlightEnabled(false);
            LineData data = new LineData(lineDataSet);
            chart.setData(data);
            chart.invalidate();
        }
    }

    /**
     * 更新图表
     *
     * @param chart     图表
     * @param values    数据
     * @param valueType 数据类型
     */
    public static void notifyDataSetChanged(LineChart chart, List<Entry> values,
                                            final int valueType) {
        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValuesProcess(valueType)[(int) value];
            }
        });

        chart.invalidate();
        setChartData(chart, values);
    }

    /**
     * x轴数据处理
     *
     * @param valueType 数据类型
     * @return x轴数据
     */
    private static String[] xValuesProcess(int valueType) {
        String[] week = {"1", "2", "3", "4", "5", "6","7","8", "9", "10", "11", "12", "13","14"};

        if (valueType == dayValue) { // 今日
            String[] dayValues = new String[7];
            long currentTime = System.currentTimeMillis();
            for (int i = 6; i >= 0; i--) {
                dayValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_day);
                currentTime -= (3 * 60 * 60 * 1000);
            }
            return dayValues;

        } else if (valueType == weekValue) { // 本周
            //宁修改
            return week;
//            String[] weekValues = new String[7];
//            Calendar calendar = Calendar.getInstance();
//            int currentWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//            for (int i = 6; i >= 0; i--) {
//                weekValues[i] = week[currentWeek - 1];
//                if (currentWeek == 1) {
//                    currentWeek = 7;
//                } else {
//                    currentWeek -= 1;
//                }
//            }
//            return weekValues;

        } else if (valueType == monthValue) { // 本月
            String[] monthValues = new String[7];
            long currentTime = System.currentTimeMillis();
            for (int i = 6; i >= 0; i--) {
                monthValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_month);
                currentTime -= (4 * 24 * 60 * 60 * 1000);
            }
            return monthValues;
        }
        return new String[]{};
    }
}
