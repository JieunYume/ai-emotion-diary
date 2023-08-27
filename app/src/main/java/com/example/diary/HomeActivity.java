package com.example.diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import android.os.AsyncTask;
import java.util.List;

public class HomeActivity extends Fragment {

    private View view;
    private Button bt_group_private;
    private RecyclerView list_group;
    private MaterialCalendarView calendarView;
    private TextView tv_date;
    private RecyclerView diary_card_list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = (ViewGroup) inflater.inflate(R.layout.acitvity_home, container, false);

        calendarView = view.findViewById(R.id.cv_calendar);
        tv_date = view.findViewById(R.id.tv_date);

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.cv_calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        tv_date = view.findViewById(R.id.tv_date);

        //캘린더 뷰에 아이콘 표시하기, 날짜 전달해서, 아이콘 표시하도록
        materialCalendarView.addDecorator(new Calender_icon(Color.RED, Collections.singleton(CalendarDay.today())));
        CalendarDay today = CalendarDay.today();
        Log.d("testt", today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일");
        tv_date.setText(today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일");


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                tv_date.setText(date.getYear() + "년 " + (date.getMonth() + 1) + "월 " + date.getDay() + "일");
            }
        });

        //날짜에 맞는 데이터를 가지고 와서 밑에 일정 표시
        return view;
    }

}