package com.example.sunny.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button proceedButton = (Button) findViewById(R.id.proceed_button_id);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               EditText uname = (EditText) findViewById(R.id.newusername_text_id);
               EditText passwd = (EditText) findViewById(R.id.newpassword_text_id);
               EditText mobile = (EditText) findViewById(R.id.mobilenumber_text_id);
               EditText email = (EditText) findViewById(R.id.emailid_text_id);
               String u = uname.getText().toString();
               String p = passwd.getText().toString();
               String m = mobile.getText().toString();
               String e = email.getText().toString();
               if(u.isEmpty() || p.isEmpty() || m.isEmpty() || e.isEmpty()) {
                   Toast.makeText(getApplicationContext(),"Blank Fields",Toast.LENGTH_SHORT).show();
               }
               else {
                    SQLiteDatabase mydatabase = openOrCreateDatabase("mydata",MODE_PRIVATE,null);
                    mydatabase.execSQL("create table if not exists mydata(Username varchar,Password VARCHAR,Mobile varchar,Email varchar);");
                    Cursor cursor = mydatabase.rawQuery("select * from mydata where Username = '"+u+"' " , null);
                    if(cursor.getCount()>0) {
                        Toast.makeText(getApplicationContext(),"User Already Exists",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mydatabase.execSQL("insert into mydata values('"+u+"' , '"+p+"' , '"+m+"' , '"+e+"');");
                        Toast.makeText(getApplicationContext(),"Successfully Signed in!",Toast.LENGTH_SHORT).show();
                        startActivity( new Intent(SignUp.this,MainActivity.class));
                        finish();
                    }


               }

            }
        });


    }
}
