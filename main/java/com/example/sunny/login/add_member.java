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

public class add_member extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Button b = (Button) findViewById(R.id.add_button_id);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 EditText e1 = (EditText) findViewById(R.id.full_name);
                 EditText e2 = (EditText) findViewById(R.id.contact_no);
                 EditText e3 = (EditText) findViewById(R.id.email_id);
                 String s1 = e1.getText().toString();
                 String s2 = e2.getText().toString();
                 String s3 = e3.getText().toString();
                if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Blank Fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase mydatabase2 = openOrCreateDatabase("mydata2", MODE_PRIVATE, null);
                    mydatabase2.execSQL("create table if not exists mydata2(Username varchar,Phone VARCHAR,Email varchar,Debit varchar,Credit varchar);");
                    Cursor cursor = mydatabase2.rawQuery("select * from mydata2 where Username = '" + s1 + "' ", null);
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Person Already Exists", Toast.LENGTH_SHORT).show();
                    } else {
                        mydatabase2.execSQL("insert into mydata2 values('" + s1 + "' , '" + s2 + "' , '" + s3 + "',' " + "0" + "',' " + "0" + "');");
                        Toast.makeText(getApplicationContext(), "Successfully Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(add_member.this, Welcome_Activity.class));
                        finish();
                    }
                }

            }
        });

    }
}
