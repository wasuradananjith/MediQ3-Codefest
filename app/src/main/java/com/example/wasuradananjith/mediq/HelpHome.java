package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpHome extends AppCompatActivity implements View.OnClickListener{
    TextView requestView,curNoView,textBack;
    ImageView btnPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_home);
        requestView = (TextView)findViewById(R.id.requestDoes);
        curNoView = (TextView)findViewById(R.id.curNoDoes);
        btnPrev = (ImageView)findViewById(R.id.prev);
        textBack = (TextView)findViewById(R.id.back);

        requestView.setOnClickListener(this);
        curNoView.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        textBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.requestDoes:
                startActivity(new Intent(HelpHome.this,HelpRequest1.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            case R.id.curNoDoes:
                startActivity(new Intent(HelpHome.this,HelpCurrentNumber.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            case R.id.prev:
                startActivity(new Intent(HelpHome.this,First.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            case R.id.back:
                startActivity(new Intent(HelpHome.this,First.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
        }
    }
}