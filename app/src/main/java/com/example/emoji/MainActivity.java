package com.example.emoji;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private Integer num=0,level;
private Float x,y;
private Button easy,hard,avg,end,start ;
private ImageView img;
private TextView txt;
private Thread thread;
private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder=new AlertDialog.Builder(this);
        txt=findViewById(R.id.score);
        img = findViewById(R.id.emoji);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                txt.setText(num+"");
            }
        });

        end=findViewById(R.id.btnEnd);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("your final score").setMessage(String.valueOf(num)).setPositiveButton("ok",null)
                        .create();
                builder.show();


            }
        });
        easy=findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level=0;
            }
        });
        hard=findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level=2;
            }
        });

        avg=findViewById(R.id.average);
        avg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level=1;
            }
        });
        start=findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread=new Thread(()-> {
                    try {


                        while (true) {

                            if (level==0){
                                Thread.sleep(1000);
                            }
                            if (level==1){
                                Thread.sleep(700);
                            }
                            if (level==2){
                                Thread.sleep(400);
                            }


                            x=(float) Math.random()*800;
                            y=(float) Math.random()*1600;
                            img.setX(x);
                            img.setY(y);

                           end.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   thread.interrupt();
                                   builder.setTitle("your final score").setMessage(String.valueOf(num)).setPositiveButton("ok",null)
                                           .create();
                                   builder.show();

                               }
                           });
                            }

                    }
                    catch (Exception e){}
                });
                thread.start();
            }
        });



    }


}