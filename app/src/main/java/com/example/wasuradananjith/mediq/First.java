package com.example.wasuradananjith.mediq;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class First extends AppCompatActivity {
    private Button btnLogout;
    private Session session;
    private static Button button1;
    private TextView needHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        final DatabaseHelper myDb = new DatabaseHelper(this);

        session = new Session(this);
        if (!session.loggedin()){
            logout();
        }
        btnLogout = (Button)findViewById(R.id.btnLogout);
        needHelp = (TextView)findViewById(R.id.help);

        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(First.this, HelpHome.class));
                overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
                finish();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(First.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Log Out...");
                alertDialog.setIcon(R.drawable.warningalert);

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to logout? Logging out may delete all your requests");

                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.save);

                // Setting Positive "Yes" Button
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        Toast.makeText(getApplicationContext(), "Logging Out",
                                Toast.LENGTH_SHORT).show();
                        logout();
                        myDb.dropTable();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();

            }
        });
        OnClickButtonListener1();
        OnClickButtonListener2();
    }

    private void logout(){
        session.setLoggedin(false);
        startActivity(new Intent(First.this,LoadingLogin.class));
        finish();
    }

    public void OnClickButtonListener1(){
        button1 = (Button)findViewById(R.id.requestButton);
        button1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.wasuradananjith.mediq.Request");
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
                        finish();
                    }
                }        );
    }
    public void OnClickButtonListener2(){
        button1 = (Button)findViewById(R.id.currentNoButton);
        button1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.wasuradananjith.mediq.CurrentNumber");
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
                        finish();
                    }
                }        );
    }
}
