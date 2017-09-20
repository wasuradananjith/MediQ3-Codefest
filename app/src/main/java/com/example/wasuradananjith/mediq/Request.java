package com.example.wasuradananjith.mediq;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Request extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editRefNo,editReqNo;
    Button btnRequest,btnViewAll,btnUpdate,btnDelete;
    ImageView btnPrev;
    TextView textBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        myDb = new DatabaseHelper(this);

        editRefNo = (EditText)findViewById(R.id.refNumber1);
        editReqNo = (EditText)findViewById(R.id.notifyNumber);
        btnRequest = (Button)findViewById(R.id.requestButton);
        btnViewAll = (Button)findViewById(R.id.viewRequests);
        btnUpdate = (Button)findViewById(R.id.updateButton);
        btnDelete = (Button)findViewById(R.id.deleteButton);
        btnPrev = (ImageView)findViewById(R.id.prev);
        textBack = (TextView)findViewById(R.id.back);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        goBack1();
        goBack2();
    }

    public void goBack1(){
        btnPrev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Request.this,First.class));
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
                        startActivity(new Intent(Request.this,First.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                }
        );
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editRefNo.getText().toString().trim());
                        stopService(new Intent(getBaseContext(),Serv.class));
                        if (deletedRows > 0)
                            Toast.makeText(Request.this, "Request Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Request.this, "Incorrect Reference Number. Please Check!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData(){
        btnRequest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ref = editRefNo.getText().toString().trim();
                        String req = editReqNo.getText().toString().trim();
                        if (ref.isEmpty() || req.isEmpty()){
                            Toast.makeText(Request.this, "Invalid details. Please check!",Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean isInserted = myDb.insertData(ref,req);
                            //startService(new Intent(getBaseContext(),Serv.class));
                            if (isInserted == true)
                                Toast.makeText(Request.this, "Request Sent",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Request.this, "Request NOT Sent",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ref = editRefNo.getText().toString().trim();
                        String req = editReqNo.getText().toString().trim();
                        if (ref.isEmpty() || req.isEmpty()){
                            Toast.makeText(Request.this, "Invalid details. Please check!",Toast.LENGTH_LONG).show();
                        }
                        else{
                            //Toast.makeText(Request.this,Serv.showCount()+"",Toast.LENGTH_LONG).show();
                            boolean isUpdated = myDb.updateData(editRefNo.getText().toString().trim(),
                                    editReqNo.getText().toString().trim());
                            if (isUpdated == true)
                                Toast.makeText(Request.this, "Request Updated",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Request.this, "Incorrect Reference Number. Please Check!",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("MYTAG", PhoneNumber.pn);
                        Cursor res = myDb.getAllData();
                        if (res.getCount()== 0){
                            showMessage("Sorry", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            //buffer.append("Request : "+res.getString(0)+"\n");
                            buffer.append("Reference No : "+res.getString(1)+"\n");
                            buffer.append("Request to Notify when : "+res.getString(2)+"\n\n");
                        }
                        showMessage("Your Requests",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void issueNotification(String ref_Number,String result,String notify_Number,int time){

    }
}

