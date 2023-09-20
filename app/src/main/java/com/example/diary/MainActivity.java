package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    CheckEmotionDialog checkEmotionDialog=(CheckEmotionDialog) CheckEmotionDialog.checkEmotionDialog;
    private String memberId;
    private String nickName;
    private String filePath;
    private PlayListActivity fragmentPlayList = new PlayListActivity();
    private StaticsActivity fragmentStats=new StaticsActivity();
    //private MyPageActivity fragmentMyPage=new MyPageActivity();
    //private WriteActivity fragmentWrite=new WriteActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        Intent intent=getIntent();
        memberId=intent.getStringExtra("memberId");
        nickName=intent.getStringExtra("nickName");
        filePath=intent.getStringExtra("filePath");

        getSupportFragmentManager().beginTransaction().add(R.id.containers, fragmentHome).commit();
        //fragmentHome.setUser_info(memberId, filePath, nickName);
        fragmentHome.setUser_info(memberId, filePath, nickName);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmotionDialog=new CheckEmotionDialog(view.getContext());
                WindowManager.LayoutParams params=checkEmotionDialog.getWindow().getAttributes();
                params.width=WindowManager.LayoutParams.WRAP_CONTENT;
                params.height=WindowManager.LayoutParams.WRAP_CONTENT;
                checkEmotionDialog.getWindow().setAttributes((WindowManager.LayoutParams)params);
                checkEmotionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                checkEmotionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                checkEmotionDialog.setId(memberId, filePath, nickName);
                checkEmotionDialog.show();
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().add(R.id.containers, fragmentHome).commit();
                        break;
                    case R.id.playlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentPlayList).commit();
                        break;
                    case R.id.stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentStats).commit();
                        break;
                    //case R.id.setting:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentMyPage).commit();
                        //break;
                    /*case R.id.floatingActionButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentWrite).commit();
                        break;*/
                }
                return true;

            }
        });

    }
}