package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ComputerOver extends AppCompatActivity {
Button start;
    TextView txtt;
    TextView con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_over);
        start=findViewById(R.id.startbutton);
       txtt=findViewById(R.id.title1);
       con=findViewById(R.id.content);


        Bundle extras=getIntent().getExtras();

        String s="Overview Page";
        if(extras!=null){
           s=extras.getString("keyOver");

           if(s.equals("Compover")){
               txtt.setText("Overview");
                con.setText(R.string.compoverview);
           } else if(s.equals("Civilover")) {
               txtt.setText("Overview");
                con.setText(R.string.civiloverview);
           }
           else if(s.equals("elecover")) {
               txtt.setText("Overview");
               con.setText(R.string.elecoverview);

           }
           else if(s.equals("mechover")) {
               txtt.setText("Overview");
               con.setText(R.string.mechoverview);

           }
           else if(s.equals("entcover")) {
               txtt.setText("Overview");
               con.setText(R.string.entcoverview);

           }
           else if(s.equals("medover")) {
               txtt.setText("Overview");
               con.setText(R.string.medoverview);

           }
        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ComputerOver.this,Compquiz.class);
                startActivity(intent);

            }
        });
    }
}