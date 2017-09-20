package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    private TextView txtStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtStart = (TextView) findViewById(R.id.getStarted);
        txtStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch ((v.getId())) {
            case R.id.getStarted: {
                startActivity(new Intent(Welcome.this, Welcome2.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            }
        }
    }
}
