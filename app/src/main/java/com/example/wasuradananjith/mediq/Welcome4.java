package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome4 extends AppCompatActivity implements View.OnClickListener{
    private ImageView btnNext2,btnPrev2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome4);

        btnNext2 = (ImageView) findViewById(R.id.next2);
        btnPrev2 = (ImageView) findViewById(R.id.previous2);
        btnNext2.setOnClickListener(this);
        btnPrev2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.next2: {
                startActivity(new Intent(Welcome4.this, Welcome5.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            }
            case R.id.previous2:{
                startActivity(new Intent(Welcome4.this, Welcome3.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            }
        }
    }
}
