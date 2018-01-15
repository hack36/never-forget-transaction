package com.example.sunny.login;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome_Activity extends AppCompatActivity implements View.OnClickListener  {
    ImageView addimg,seeimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        addimg=(ImageView)findViewById(R.id.img1);
        seeimg=(ImageView)findViewById(R.id.img2);

        addimg.setOnClickListener(this);
        seeimg.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(v==addimg){
            Intent i1=new Intent(Welcome_Activity.this,add_member.class);
            startActivity(i1);

        }
        if(v==seeimg)
        {
            Intent i2= new Intent(Welcome_Activity.this,view_member.class);
            startActivity(i2);
        }

    }
}