package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {
TextView t1,t2;
CheckBox ch1,ch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.btntext1);
        t2=findViewById(R.id.btntext2);
        ch1=findViewById(R.id.checkbox1);
        ch2=findViewById(R.id.checkbox2);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ch1.isChecked()) {
                    ch1.setChecked(true);
                    Intent intent=new Intent(MainActivity1.this,Form_Activity.class);
                    startActivity(intent);
                }
                else{
                    ch1.setChecked(false);
                }

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ch2.isChecked()) {
                    ch2.setChecked(true);

                }
                else{
                    ch2.setChecked(false);
                }
            }
        });
    }
}