package com.example.sunny.login;

import android.content.ContentValues;
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

public class EditActivity extends AppCompatActivity {
    TextView nametv;
    EditText et1,et2;
    Button btn;
    Bundle extra;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        extra = getIntent().getExtras();
        str = extra.getString("string");
        nametv = (TextView)findViewById(R.id.textid);
        nametv.setText(str);
        et1=(EditText)findViewById(R.id.debit);
        et2=(EditText)findViewById(R.id.credit);
        btn=(Button)findViewById(R.id.savebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                SQLiteDatabase mydatabase2 = openOrCreateDatabase("mydata2", MODE_PRIVATE, null);
                Cursor resultSet = mydatabase2.rawQuery("select * from mydata2 where Username = '" + str + "' ", null);
                resultSet.moveToFirst();
                String deb = resultSet.getString(resultSet.getColumnIndex("Debit"));
                String cre = resultSet.getString(resultSet.getColumnIndex("Credit"));
                resultSet.close();
                if (s1.equals("") && s2.equals(""))
                    Toast.makeText(getApplicationContext(), "Blank Fields!", Toast.LENGTH_SHORT).show();
                else {
                    int i,j;
                    if (s1.equals("") || s1.equals("0"))
                         i = 0;
                    else
                        i = Integer.parseInt(s1.trim());
                    if (s2.equals("") || s2.equals("0"))
                        j = 0;
                    else
                        j = Integer.parseInt(s2.trim());
                    int in = Integer.parseInt(deb.trim());
                    int jn = Integer.parseInt(cre.trim());
                        i = i + in;
                        j = j + jn;
                        if (i >= j) {
                            i = i - j;
                            j = 0;
                        } else {
                            j = j - i;
                            i = 0;
                        }
                    s1 = Integer.toString(i);
                    s2 = Integer.toString(j);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Debit", s1);
                    contentValues.put("Credit", s2);
                    mydatabase2 = openOrCreateDatabase("mydata2", MODE_PRIVATE, null);
                    mydatabase2.update("mydata2", contentValues, "Username = ?", new String[]{str});
                    Toast.makeText(getApplicationContext(), "Successfully Added!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditActivity.this, Welcome_Activity.class));
                    finish();
                }
            }


        });

    }
}
