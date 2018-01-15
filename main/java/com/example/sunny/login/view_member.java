package com.example.sunny.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_member extends AppCompatActivity {
    public static final String NAME = "Username";
    public static final String DEBIT = "Debit";
    public static final String CREDIT = "Credit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);
        ArrayList<DataModel> arrayList = new ArrayList<DataModel>();
        SQLiteDatabase mydatabase = openOrCreateDatabase("mydata2",MODE_PRIVATE,null);
        Cursor res =  mydatabase.rawQuery( "select * from mydata2", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            arrayList.add(new DataModel(res.getString(res.getColumnIndex(NAME)),res.getString(res.getColumnIndex(DEBIT)),res.getString(res.getColumnIndex(CREDIT))));
            res.moveToNext();
        }
        DetailAdapter detailAdapter = new DetailAdapter(this,arrayList);
        ListView listView = (ListView) findViewById(R.id.listview_id);
        listView.setAdapter(detailAdapter);

    }

}
