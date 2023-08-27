/*package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public FloatingActionButton floatingActionButton;

    private HomeActivity fragmentHome = new HomeActivity();     //홈
    private PlayListActivity fragmentPlayList = new PlayListActivity();
    private StatsActivity fragmentStats=new StatsActivity();
    private MyPageActivity fragmentMyPage=new MyPageActivity();
    private WriteActivity fragmentWrite=new WriteActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        floatingActionButton=findViewById(R.id.floatingActionButton);

        getSupportFragmentManager().beginTransaction().add(R.id.containers, fragmentHome).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.playlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentPlayList).commit();
                        break;
                    case R.id.stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentStats).commit();
                        break;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentMyPage).commit();
                        break;
                    case R.id.floatingActionButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentWrite).commit();
                        break;
                }
                return true;

            }
        });

    }
}*/