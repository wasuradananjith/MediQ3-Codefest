package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnNext,btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        btnNext = (ImageView) findViewById(R.id.next);
        btnPrev = (ImageView) findViewById(R.id.previous);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.next: {
                startActivity(new Intent(Welcome2.this, Welcome3.class));
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
            }
            case R.id.previous:{
                startActivity(new Intent(Welcome2.this, Welcome.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            }
        }
    }
}
