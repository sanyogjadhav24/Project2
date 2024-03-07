package com.example.loginsignup;



import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuideInformationLayout extends AppCompatActivity implements PaymentResultListener {
TextView name,about,time,date,fee,meet;
Button enroll;

CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_information_layout);
//     new Handler().postDelayed(new Runnable() {
//         @Override
//         public void run() {
//             Intent intent=new Intent(GuideInformationLayout.this, Meetlink.class);
//             startActivity(intent);
//             finish();
//         }
//     },5000);

       Checkout.preload(getApplicationContext());


        name=findViewById(R.id.nameid1);
        about=findViewById(R.id.aboutid);

        time=findViewById(R.id.timeid);

        date=findViewById(R.id.dateid);

        fee=findViewById(R.id.feee1);
        img=findViewById(R.id.user1);
enroll=findViewById(R.id.button3);
meet=findViewById(R.id.meet);

        Bundle extras=getIntent().getExtras();
        String s="Overview Page";
        if(extras!=null){
            s=extras.getString("keyOver");

            if(s.equals("Compover")){
          name.setText(R.string.Namecomp);
                about.setText(R.string.compexp);
                img.setImageResource(R.drawable.u2);
                time.setText(R.string.Timecomp);
                date.setText(R.string.Datecomp);
enroll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        makepayment();
    }
});

    fee.setText(R.string.Feescomp);
            }
            else if(s.equals("Civilover")) {
                name.setText(R.string.Namecivil);
                about.setText(R.string.civilexp);
                img.setImageResource(R.drawable.u5);
                time.setText(R.string.Timecivil);
                date.setText(R.string.Datecivil);
                fee.setText(R.string.Feescivil);
                enroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makepayment();
                    }
                });
            }
            else if(s.equals("elecover")) {
                name.setText(R.string.Nameelec);
                about.setText(R.string.elecexp);
                time.setText(R.string.Timeelec);
                date.setText(R.string.Dateelec);

                img.setImageResource(R.drawable.u2);
                fee.setText(R.string.Feeselec);
                enroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makepayment();
                    }
                });
            }
            else if(s.equals("mechover")) {
                name.setText(R.string.Namemech);
                time.setText(R.string.Timemech);
                date.setText(R.string.Datemech);
                fee.setText(R.string.Feesmech);
                about.setText(R.string.mechxp);
                img.setImageResource(R.drawable.u4);
                enroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makepayment();
                    }
                });
            }
            else if(s.equals("entcover")) {
                name.setText(R.string.Nameentc);
                about.setText(R.string.entcexp);
                img.setImageResource(R.drawable.u3);
                time.setText(R.string.Timeentc);
                date.setText(R.string.Dateentc);

                fee.setText(R.string.Feesentc);

            }
            else if(s.equals("medover")) {
                name.setText(R.string.Namemed);
                about.setText(R.string.medexp);
                img.setImageResource(R.drawable.u6);
                time.setText(R.string.Timemed);
                date.setText(R.string.Datemed);
                fee.setText(R.string.Feesmed);
                enroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makepayment();
                    }
                });
            }
        }

    }

    private void makepayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_jVJlRY22BEf8O2");

        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Sanyog Jadhav");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "100000");//pass amount in currency subunits
            options.put("prefill.email", "jadhavsanyog.400@gmail.com");
            options.put("prefill.contact","8600596593");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, " Payment Successful", Toast.LENGTH_SHORT).show();
        meet.setText(R.string.hyperlink);
        meet.setMovementMethod(LinkMovementMethod.getInstance());
        enroll.setText("Registered");
        enroll.setEnabled(false);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Failed Payment id:"+s, Toast.LENGTH_SHORT).show();
    }
}