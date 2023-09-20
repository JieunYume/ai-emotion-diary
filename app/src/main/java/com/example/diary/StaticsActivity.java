package com.example.diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StaticsActivity extends Fragment {
    BarData chartData;
    private View view;
    private BarChart chart;
    private TextView tv_date;
    private RelativeLayout ll_check;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private void updateLabel() {
        String myFormat = "yyyy년 MM월";    // 출력형식   2021/07/26
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);


        tv_date.setText(sdf.format(myCalendar.getTime()));

        String a=tv_date.getText().toString().split(" ")[0]+" "+tv_date.getText().toString().split(" ")[1];

        if(tv_date.getText().toString().equals("2023년 08월"))
        {
            chartData=new BarData();
            setData(chart, Arrays.asList(10, 50, 10, 10, 10, 10));
        }
        if(tv_date.getText().toString().equals("2023년 09월"))
        {
            chartData=new BarData();
            setData(chart, Arrays.asList(30, 20, 20, 10, 10, 10));
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = (ViewGroup) inflater.inflate(R.layout.activity_statics, container, false);
        chart=view.findViewById(R.id.chart);
        tv_date=view.findViewById(R.id.tv_date);
        ll_check=view.findViewById(R.id.ll_check);
        setData(chart, Arrays.asList(30, 20, 20, 10, 10, 10));

        CalendarDay today = CalendarDay.today();
        String date=today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일";
        tv_date.setText(date);

        ll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getContext(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        //차트 제일 높은거 색깔

        return view;



    }

    private void initBarChart(BarChart chart) {
        // 차트 회색 배경 설정 (default = false)
        chart.setDrawGridBackground(false);
        // 막대 그림자 설정 (default = false)
        chart.setDrawBarShadow(false);
        // 차트 테두리 설정 (default = false)
        chart.setDrawBorders(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);  // 차트 범례 설정(legend object chart)


        // X, Y 바의 애니메이션 효과
        chart.animateY(1000);
        chart.animateX(1000);

        // 바텀 좌표 값
        XAxis xAxis = chart.getXAxis();
        // x축 위치 설정
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 그리드 선 수평 거리 설정
        xAxis.setTextSize(10);

// 레이블 텍스트 색
        xAxis.setTextColor(Color.BLACK);
// 축 색
        xAxis.setAxisLineColor(Color.BLACK);
// 그래프 뒷 배경의 그리드 표시하지 않기
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
// 레이블 간격 : 1로 하면 1단위로 레이블 생김
// xAxis.setSpaceMax(1f);
// xAxis.setSpaceMin(1f);
// 축을 숫자가 아니라 날짜로 표시 🤪 이 부분은 많이 헤맸는데... 추후 자세한 설명 글을 작성할 예정임!
// 축 레이블 표시 간격 : 2로 하면 2칸마다 레이블 표시
// xAxis.setGranularity(2f);
// xAxis.setGranularityEnabled(true);

        YAxis yAxis;
        yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        xAxis.setTextSize(10);
        yAxis.setTextSize(13);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
// yAxis.setSpaceMax(0.2f);
// yAxis.setSpaceMin(0.2f);
    }
    private void setData(BarChart chart, List<Integer> y)
    {
        initBarChart(chart);
        chart.setScaleEnabled(false);

        ArrayList<BarEntry> entry_chart1 = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<String> xVals = new ArrayList<String>(); // 변환할 String 형태 x축 값

        xVals.clear(); // x축 배열 모든 값 제거
        entry_chart1.clear();



        chartData=new BarData();
        //감정 분석 api
        entry_chart1.add(new BarEntry(0, y.get(0))); //entry_chart1에 좌표 데이터를 담는다.
        entry_chart1.add(new BarEntry(1,  y.get(1)));
        entry_chart1.add(new BarEntry(2, y.get(2)));
        entry_chart1.add(new BarEntry(3, y.get(3)));
        entry_chart1.add(new BarEntry(4, y.get(4)));
        entry_chart1.add(new BarEntry(5, y.get(5)));

        xVals.add("기쁨");
        xVals.add("당황");
        xVals.add("분노");
        xVals.add("불안");
        xVals.add("상처");
        xVals.add("슬픔");

        BarDataSet barDataSet = new BarDataSet(entry_chart1, "bardataset"); // 데이터가 담긴 Arraylist 를 BarDataSet 으로 변환한다.



        chartData.addDataSet(barDataSet); // 해당 BarDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

        barDataSet.setValueTextSize(14);


        List<Integer> color;
        barDataSet.setColors(Color.rgb(254, 241, 171), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181));
        color = new ArrayList<Integer>(Arrays.asList(Color.rgb(191, 138, 2), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32)));
        barDataSet.setValueTextColors(color);

        chart.setData(chartData); // 차트에 위의 DataSet 을 넣는다.


        XAxis xAxis = chart.getXAxis(); // XAxis : x축 속성 설정하기 위해서 xAxis 객체 만들어줌
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        chart.invalidate(); // 차트 업데이트

    }

}