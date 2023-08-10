package com.example.diary;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

//캘린더에 아이콘 표시하는 클래스
public class Calender_icon implements DayViewDecorator {
    private final int color;
    private final HashSet<CalendarDay> dates;

    public Calender_icon(int color, Collection<CalendarDay> dates){
        this.color=color;
        this.dates=new HashSet<>(dates);
    }
    @Override
    public boolean shouldDecorate(CalendarDay day){
        return dates.contains(day);
    }
    @Override
    //다만 지금은 점으로 표시하도록 되어 있음. 이거를 아이콘 이미지로 봐꿔야함
    public void decorate(DayViewFacade view){
        view.addSpan(new DotSpan(5, color));
    }

}
