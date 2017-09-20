package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentNumber extends AppCompatActivity {
    EditText refNumber2;
    private static Button btnOk;
    ImageView btnPrev;
    TextView textBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_number);
        refNumber2 = (EditText)findViewById(R.id.refNumber2);
        btnOk = (Button)findViewById(R.id.currentNoButton);
        btnPrev = (ImageView)findViewById(R.id.prev);
        textBack = (TextView)findViewById(R.id.back);
        OnClickButtonListener1();
        goBack1();
        goBack2();

    }

    public void goBack1(){
        btnPrev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CurrentNumber.this,First.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                }
        );
    }

    public void goBack2(){
        textBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CurrentNumber.this,First.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                }
        );
    }

    public void OnClickButtonListener1(){
        btnOk.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String refnumber2 = refNumber2.getText().toString();
                        String type = "checkCurrentNo";
                        if (refnumber2.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Reference Number field is empty", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            BackgroundWorker backgroundWorker = new BackgroundWorker(CurrentNumber.this);
                            backgroundWorker.execute(type,refnumber2);
                        }
                    }
                }        );
    }

    /*public void OnPressCurNo(View view){
        String refnumber2 = refNumber2.getText().toString();
        String type = "checkCurrentNo";
        if (refnumber2.isEmpty()){
            Toast.makeText(getApplicationContext(),"Reference Number field is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type,refnumber2);
        }

    }*/
}
