package com.example.sunny.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = (Button) findViewById(R.id.login_button_id);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText uname = (EditText) findViewById(R.id.username_text_id);
                EditText passwd = (EditText) findViewById(R.id.password_text_id);
                String u = uname.getText().toString();
                String p = passwd.getText().toString();
                if(u.isEmpty() || p.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Blank Fields",Toast.LENGTH_SHORT).show();
                }
                else  {
                    SQLiteDatabase mydatabase = openOrCreateDatabase("mydata",MODE_PRIVATE,null);
                    mydatabase.execSQL("create table if not exists mydata(Username varchar,Password VARCHAR,Mobile varchar,Email varchar);");
                    Cursor cursor = mydatabase.rawQuery("select * from mydata where Username = '"+u+"' and Password = '"+p+"';" , null);
                    if(cursor.getCount()<=0) {
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent i = new Intent(MainActivity.this,Welcome_Activity.class);
                        startActivity(i);
                    }

                }
            }
        });
        TextView signButton = (TextView) findViewById(R.id.sign_button_id);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}
