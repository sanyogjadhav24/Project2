package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;




public class formGuide extends AppCompatActivity {

    public  EditText e1,e2,e3,e4,e5,e6,e7;
    public  Button b1;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_guide);
        db=FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        e1=findViewById(R.id.signup_name);
        e2=findViewById(R.id.signup_Edu);
    e7=findViewById(R.id.signup_fee);
        e3=findViewById(R.id.signup_exper);
        e4=findViewById(R.id.signup_avai);
        e5=findViewById(R.id.email1);
        e6=findViewById(R.id.pass1);
        b1=findViewById(R.id.submit_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = e1.getText().toString().trim();
                String edu = e2.getText().toString().trim();
                String expe = e3.getText().toString().trim();
                String email = e5.getText().toString().trim();
                String pass = e6.getText().toString().trim();
                String avai = e4.getText().toString().trim();
                String fee = e7.getText().toString().trim();

                Map<String, Object> user2 = new HashMap<>();
                user2.put("Name", name);
                user2.put("Email",email);
                user2.put("Password",pass);
                user2.put("Education", edu);
                user2.put("Experiance", expe);
                user2.put("Available Time", avai);
                user2.put("Fees", fee);

                if (name.isEmpty()) {
                    e1.setError("Name cannot be empty");
                }
                if (edu.isEmpty()) {
                    e2.setError("Education cannot be empty");
                }
                if (expe.isEmpty()) {
                    e3.setError("Skills cannot be empty");
                }
                if (avai.isEmpty()) {
                    e4.setError("Activity cannot be empty");
                }
                if (fee.isEmpty()) {
                    e4.setError("Fees field cannot be empty");
                }

                if (email.isEmpty()) {
                    e4.setError("Email cannot be empty");
                }

                if (pass.isEmpty()) {
                    e4.setError("Password cannot be empty");
                }



                else {
                    if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (!pass.isEmpty()) {
                            auth.signInWithEmailAndPassword(email, pass)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            Toast.makeText(formGuide.this, "Submitted SuccessFully", Toast.LENGTH_SHORT).show();

                                            startActivity(new Intent(formGuide.this, LoginActivity3.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(formGuide.this, "Submission Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            e6.setError("Empty fields are not allowed");
                        }
                    } else if (email.isEmpty()) {
                        e5.setError("Empty fields are not allowed");
                    } else {
                        e5.setError("Please enter correct email");
                    }

                    db.collection("guidesInfo")
                            .add(user2)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(formGuide.this, "Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NotNull Exception e) {
                                    Toast.makeText(formGuide.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }


            }

        });



    }
}