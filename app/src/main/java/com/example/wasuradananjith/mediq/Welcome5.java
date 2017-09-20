package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome5 extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnNext3,btnPrev3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome5);

        btnNext3 = (ImageView) findViewById(R.id.finish);
        btnPrev3 = (ImageView) findViewById(R.id.previous3);
        btnNext3.setOnClickListener(this);
        btnPrev3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.finish: {
                startActivity(new Intent(Welcome5.this, First.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            }
            case R.id.previous3:{
                startActivity(new Intent(Welcome5.this, Welcome4.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            }
        }
    }
}
