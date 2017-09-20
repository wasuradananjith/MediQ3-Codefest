package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpRequest2 extends AppCompatActivity implements View.OnClickListener{
    TextView textBack,textNext;
    ImageView btnPrev,btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_request2);

        btnPrev = (ImageView)findViewById(R.id.prev);
        textBack = (TextView)findViewById(R.id.back);

        btnNext = (ImageView)findViewById(R.id.forward);
        textNext = (TextView)findViewById(R.id.next);

        btnPrev.setOnClickListener(this);
        textBack.setOnClickListener(this);

        btnNext.setOnClickListener(this);
        textNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.prev:
                startActivity(new Intent(HelpRequest2.this, HelpRequest1.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                break;
            case R.id.back:
                startActivity(new Intent(HelpRequest2.this, HelpRequest1.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                break;
            case R.id.forward:
                startActivity(new Intent(HelpRequest2.this, HelpRequest3.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
            case R.id.next:
                startActivity(new Intent(HelpRequest2.this, HelpRequest3.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
    }
}
