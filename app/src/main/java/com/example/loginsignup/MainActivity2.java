package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;




public class MainActivity2 extends AppCompatActivity {
    TextView t1,t2;
    CheckBox ch1,ch2;
    public static final String SHARED_PREFS="shared";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//       checkBox();
        t1=findViewById(R.id.btntext1);
        t2=findViewById(R.id.btntext2);
        ch1=findViewById(R.id.checkbox1);
        ch2=findViewById(R.id.checkbox2);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ch1.isChecked()) {
                    ch1.setChecked(true);
                    Intent intent=new Intent(MainActivity2.this, SignUpActivity.class);
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
                    Intent intent=new Intent(MainActivity2.this,SignupActivity2.class);
                    startActivity(intent);

                }
                else{
                    ch2.setChecked(false);
                }
            }
        });


    }
//    private void checkBox() {
//        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//        String check=sharedPreferences.getString("name","");
//        if(check.equals("true")){
//            //   Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();
//            Log.d("onclicklogout","afterclick");
//            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
//            finish();
//
//        }
//    }
}