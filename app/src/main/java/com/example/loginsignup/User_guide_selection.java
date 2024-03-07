package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.firestore.auth.User;

public class User_guide_selection extends AppCompatActivity {
    RelativeLayout comp,civil,elec,mech,entc,med;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide_selection);

        comp=findViewById(R.id.complay);
        civil=findViewById(R.id.civillay);
        elec=findViewById(R.id.eleclay);
        mech=findViewById(R.id.mechlay);
        entc=findViewById(R.id.entclay);
        med=findViewById(R.id.medlay);


        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","Compover");
                startActivity(intent);


            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","Civilover");
                startActivity(intent);


            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","elecover");
                startActivity(intent);


            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","mechover");
                startActivity(intent);


            }
        });
        entc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","entcover");
                startActivity(intent);


            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_guide_selection.this,GuideInformationLayout.class);
                intent.putExtra("keyOver","medover");
                startActivity(intent);


            }
        });
    }
}