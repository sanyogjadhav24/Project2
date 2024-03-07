package com.example.loginsignup;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class SignupActivity2 extends AppCompatActivity {

    private FirebaseAuth auth1;
    private FirebaseFirestore db;

    public static final String SHARED_PREFS="shared";
    private EditText signupEmail, signupPassword;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        auth1= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        checkBox();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        Button signupButton = findViewById(R.id.signup_button);
        TextView loginRedirectText = findViewById(R.id.content);

        signupButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String user = signupEmail.getText().toString().trim();
                                                String pass = signupPassword.getText().toString().trim();
                                                Map<String, Object> user1 = new HashMap<>();
                                                user1.put("Email", user);
                                                user1.put("Password", pass);
                                                if (user.isEmpty()) {
                                                    signupEmail.setError("Email cannot be empty");
                                                }
                                                if (pass.isEmpty()) {
                                                    signupPassword.setError("Password cannot be empty");
                                                } else {
                                                    auth1.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful()) {
                                                                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                                                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                                                editor.putString("name","true");
                                                                editor.apply();
                                                                Toast.makeText(SignupActivity2.this, "SignUp Successful", Toast.LENGTH_SHORT).show();

                                                                Intent intent = new Intent(SignupActivity2.this, formGuide.class);
                                                                startActivity(intent);
                                                            } else {
                                                                Toast.makeText(SignupActivity2.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                                    db.collection("user")
                                                            .add(user1)
                                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                @Override
                                                                public void onSuccess(DocumentReference documentReference) {
                                                                    Toast.makeText(SignupActivity2.this, "Successful", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NotNull Exception e) {

                                                                    Toast.makeText(SignupActivity2.this, "Failed", Toast.LENGTH_SHORT).show();


                                                                }
                                                            });
                                                }


                                            }
                                        }
        );
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity2.this, LoginActivity3.class);
                startActivity(intent);
            }
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check=sharedPreferences.getString("name","");
        if(check.equals("true")){
            //   Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity2.this, GuideHome.class));
            finish();
        }
    }
}
