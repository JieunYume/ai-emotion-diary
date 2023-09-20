package com.example.diary;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ProgressDialog extends Dialog
{
    private ImageView pg;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
        Animation animation= AnimationUtils.loadAnimation(context, R.anim.rotate);
        super.onCreate(savedInstanceState);
        pg=findViewById(R.id.pg);
        pg.startAnimation(animation);
    }

    public ProgressDialog(Context context)
    {
        super(context);
        this.context=context;
        // 다이얼 로그 제목을 안보이게...


    }
}