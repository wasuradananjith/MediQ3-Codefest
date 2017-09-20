package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome3 extends AppCompatActivity implements View.OnClickListener{
    private ImageView btnNext1,btnPrev1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);

        btnNext1 = (ImageView) findViewById(R.id.next1);
        btnPrev1 = (ImageView) findViewById(R.id.previous1);
        btnNext1.setOnClickListener(this);
        btnPrev1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.next1: {
                startActivity(new Intent(Welcome3.this, Welcome4.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            }
            case R.id.previous1:{
                startActivity(new Intent(Welcome3.this, Welcome2.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
                break;
            }
        }
    }
}
