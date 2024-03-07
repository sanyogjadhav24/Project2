package com.example.loginsignup;

import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.AccessControlContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Userprofile extends AppCompatActivity {


    Button earn,noti,edit,logout;
    ImageView profilepic,pen1;
    TextView name,edu,exp,avai,profname;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    StorageReference storageReference;
    ProgressDialog progressDialog;
    ActivityResultLauncher<Intent> imagepicklauncher;
    Uri selectedImageUri;
    GoogleSignInClient gClient;

    GoogleSignInOptions gOptions;
    FirebaseFirestore db=null;
    public static final String SHARED_PREFS="shared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        name=findViewById(R.id.nameid1);
        edu=findViewById(R.id.nameid2);
        edit=findViewById(R.id.editis);
        exp=findViewById(R.id.nameid3);

        avai=findViewById(R.id.nameid4);
        profilepic=findViewById(R.id.profile);
        profname=findViewById(R.id.nameid5);
        pen1=findViewById(R.id.update1);
logout=findViewById(R.id.button2);
        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);

        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null){
            String gName = gAccount.getDisplayName();
            name.setText(gName);
        }

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        gClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 finish();
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name","");
                editor.apply();

                startActivity(new Intent(Userprofile.this, LoginActivity.class));
                finish();
            }
        });
    }
});

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("guidesInfo");
//        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                Log.d("dname4","before sanpshot");
//                if (task.isSuccessful()){
//
//                    if (task.getResult().exists()){
//
//                        Toast.makeText(GuideHome.this,"Successfully Read",Toast.LENGTH_SHORT).show();
//                        DataSnapshot dataSnapshot = task.getResult();
//                        String firstName = String.valueOf(dataSnapshot.child("Name").getValue());
//                        String lastName = String.valueOf(dataSnapshot.child("Education").getValue());
//                        String Experiance = String.valueOf(dataSnapshot.child("Experiance").getValue());
//                        String available_time = String.valueOf(dataSnapshot.child("Available Time").getValue());
//                        Log.d("dname4","before setting");
//                        profname.setText(firstName);
//                        name.setText(firstName);
//                        edu.setText(lastName);
//                        exp.setText(Experiance);
//                        avai.setText(available_time);
//
//
//
//                    }
//
//
//                }else {
//
//                    Toast.makeText(GuideHome.this,"Failed to read",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

//
//       Log.d("dname1","before calling");
//       getData();
//     Log.d("dname2","After calling");




        imagepicklauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data=result.getData();
                        if(data!=null && data.getData()!=null){
                            selectedImageUri=data.getData();
                            setProfilpic(getBaseContext(),selectedImageUri,profilepic);
                        }
                    }
                }
        );



        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(Userprofile.this).cropSquare().compress(512).maxResultSize(512,512)
                        .createIntent(new Function1<Intent, Unit>() {
                            @Override
                            public Unit invoke(Intent intent) {
                                imagepicklauncher.launch(intent);
                                return null;
                            }
                        });

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// ImagePicker.with(GuideHome.this).cropSquare().compress(512).maxResultSize(512,512)
                uploadImage();
                storageReference.getDownloadUrl()
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                Uri uri=task.getResult();
                                setProfilpic(getBaseContext(),uri,profilepic);
                            }
                        });
            }
        });

    }

    public void getData() {
        db.collection("guidesInfo").document("Ss7rBllzexFPWBakyEW5")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // DocumentSnapshot contains the data read from the document
                            Map<String, Object> data = documentSnapshot.getData();
                            StringBuilder stringBuilder = new StringBuilder();
                            if (data != null) {
                                for (Map.Entry<String, Object> entry : data.entrySet()) {
                                    stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                                }
                                profname.setText(stringBuilder.toString());
                                Log.d("mytag1", "DocumentSnapshot data: " + data);
                            } else {
                                Log.d("my-tag2", "No data found in document");
                            }
                        } else {
                            Log.d("my-tag3", "No such document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("mytag4", "Error getting document", e);
                        Toast.makeText(Userprofile.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setProfilpic(Context context, Uri selectedImageUri, ImageView profilepic) {
        Glide.with(context).load(selectedImageUri).apply(RequestOptions.circleCropTransform()).into(profilepic);

    }
    private void uploadImage(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading File......");
        progressDialog.show();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy_MM_DD_HH_mm_ss", Locale.CANADA);
        Date now= new Date();
        String filename=formatter.format(now);
        storageReference=FirebaseStorage.getInstance().getReference("images/"+filename);

        storageReference.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        profilepic.setImageURI(null);


                        Toast.makeText(Userprofile.this,"SuccessFully Uploaded",Toast.LENGTH_SHORT).show();

                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Toast.makeText(Userprofile.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                });



    }


}