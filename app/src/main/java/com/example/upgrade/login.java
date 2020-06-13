package com.example.upgrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button SINGUP, login_btn;
    TextInputLayout username,edittext2 ;
    private View progressBar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //btn=findViewById(R.id.login);
        SINGUP = findViewById(R.id.sing_up);
        login_btn = findViewById(R.id.login);
        username=findViewById(R.id.username);
        edittext2=findViewById(R.id.password);

        Button login_btn = findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
            }
        });


        SINGUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Sing_up.class);
                startActivity(intent);
            }
        });
    }
    public void loginUser(View view){

        String email,password;
        email=username.getEditText().getText().toString();
        password=edittext2.getEditText().getText().toString();
        if (email.length()!=0 && password.length()!=0) {
            //ErrorAnnouncer.setText("");
            Loginchecker(email, password);
        }
        else{
//            ErrorAnnouncer.setText("One or more fields are not filled!");
            announcer("One or more fields are not filled!");
        }
    }
    public void Loginchecker(final String username, final String password){
        rootNode= FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String passwordFromDB = dataSnapshot.child(username).child("password").getValue(String.class);
                    if(passwordFromDB.equals(password)){
//                        String nickname;
//                        nickname = dataSnapshot.child(nickname).child("nickname").getValue(String.class);
                        nextpage();
                    }
                    else{
                        //ErrorAnnouncer.setText("Wrong Creditentials");
                        announcer("Wrong Creditentials");
                    }
                }
                else{
                    //ErrorAnnouncer.setText("Wrong Creditentials");
                    announcer("Wrong Creditentials");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void nextpage() {
        Intent intent = new Intent(this, UserProfile.class);
        //intent.putExtra("nickname", nickname);
        startActivity(intent);
    }
    public void announcer(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
//    private Boolean validateUsername() {
//        String val = username.getEditText().getText().toString();
//        if (val.isEmpty()) {
//            username.setError("Field cannot be empty");
//            return false;
//        } else {
//            username.setError(null);
//            username.setErrorEnabled(false);
//            return true;
//        }
//
//    }
//
//    private Boolean validatePassword() {
//        String val = password.getEditText().getText().toString();
//        if (val.isEmpty()) {
//            password.setError("Field cannot be empty");
//            return false;
//        } else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }

//    public void loginUser(View view) {
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        } else {
//            isUser();
//        }
//    }










//    private void isUser() {
//        final String usernameentered = "username";
//        final String passwordentered = "password";
//        reference = rootNode.getReference("users");
//        Query checkUser = reference.orderByChild("username").equalTo(usernameentered);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    username.setError(null);
//                    username.setErrorEnabled(false);
//
//                    String passwordFromDB = dataSnapshot.child(usernameentered).child("password").getValue(String.class);
//                    if (passwordFromDB.equals(passwordentered)) {
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//                        //String nameFromDB=dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);String usernameFromDB=dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
//                        //String phoneNoFromDB = dataSnapshot.child(usernameentered).child("PhoneNO").getValue(String.class);
//                        //String emailFromDB = dataSnapshot.child(usernameentered).child("email").getValue(String.class);
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//
//                        //intent.putExtra("name",nameFromDB);
//                        //intent.putExtra("username",usernameFromDB);
//                        //intent.putExtra("phoneNo",phoneNoFromDB);
//                        // intent.putExtra("email",emailFromDB);
//                        startActivity(intent);
//                    } else {
//                        progressBar.setVisibility(View.GONE);
//                        password.setError("Wrong Password");
//                        password.requestFocus();
//                    }
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    username.setError("No such User exist");
//                    username.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    String passwordFromDB = dataSnapshot.child("password").getValue(String.class);
//                    if (passwordFromDB.equals(passwordentered)) {
//                        //String firstname = dataSnapshot.child("firstname").getValue(String.class);
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//                        startActivity(intent);
//
//
//                    } else {
//                        //progressBar.setVisibility(View.GONE);
//                        password.setError("Wrong Password");
//                        password.requestFocus();
//                    }
//                } else {
//                   // progressBar.setVisibility(View.GONE);
//                    username.setError("No such User exist");
//                    username.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }


    //    private void isUser()
//    {
//        //progressBar.setVisibility(View.VISIBLE);
//         final String userEnteredPassword=password.getEditText().getText().toString().trim();
//         final String userEnteredUsername=username.getEditText().getText().toString().trim();
//
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser=reference.orderByChild("username").equalTo(userEnteredUsername);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//
  //  }





//                if (dataSnapshot.exists())
//                {
//                username.setError(null);
//                username.setErrorEnabled(false);
//
//                    String passwordFromDB= dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
//
//                    if (passwordFromDB.equals(userEnteredPassword))
//                    {
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//
//                        //String nameFromDB=dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
//                        String usernameFromDB=dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
//                        String phoneNoFromDB=dataSnapshot.child(userEnteredUsername).child("PhoneNO").getValue(String.class);
//                        String emailFromDB=dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//
//                        //intent.putExtra("name",nameFromDB);
//                        //intent.putExtra("username",usernameFromDB);
//                        //intent.putExtra("phoneNo",phoneNoFromDB);
//                       // intent.putExtra("email",emailFromDB);
//                        startActivity(intent);
//                    }
//
//                   else {
//                    progressBar.setVisibility(View.GONE);
//                    password.setError("Wrong Password");
//                    password.requestFocus();
//                }
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    username.setError("No such User exist");
//                    username.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }






