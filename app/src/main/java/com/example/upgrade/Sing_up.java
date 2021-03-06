package com.example.upgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sing_up extends AppCompatActivity {
    //public static final Float EXTRASPEEDLIMIT= Float.valueOf("speedlimit");
    Button SINGIN,regBtn;
    TextInputLayout regUserName, regPhone,regSpeedLimit,regPassword;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        regBtn=findViewById(R.id.register);
        SINGIN=findViewById(R.id.Sing_in);
        regUserName=findViewById(R.id.username);
        regPhone=findViewById(R.id.phone);
        regSpeedLimit=findViewById(R.id.email);
        regPassword=findViewById(R.id.password);


        SINGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sing_up.this, login.class);
                startActivity(intent);
            }
        });


                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");


            }
            private  Boolean validateUsername()
            {
                String val =regUserName.getEditText().getText().toString();
                String noWhiteSpace="(?=\\s+$)";
                if(val.isEmpty())
                {
                    regUserName.setError("field cannot be empty");
                    return false;
                }
                else if (val.length()>=15)
                {
                    regUserName.setError("username too long");
                    return false;
                }
                else if(val.matches(noWhiteSpace)) {
                    regUserName.setError("White Spaces are not allowed");
                    return false;


            }
                else{
                    regUserName.setError(null);
                    return true;
                }
            }
    private  Boolean validateEmail()
    {
        String val =regSpeedLimit.getEditText().getText().toString();
        //String emailPattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        //String noWhiteSpace="(?=\\s+$)";
        if(val.isEmpty())
        {
            regSpeedLimit.setError("field cannot be empty");
            return false;
        }
//        else if (!val.matches(noWhiteSpace)){
//            regSpeedLimit.setError("Invalid speedlimit");
//            return false;
//        }
        else{
            regSpeedLimit.setError(null);
            return true;
        }
    }
    private  Boolean validatePhoneNO()
    {
        String val =regPhone.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regPhone.setError("field cannot be empty");
            return false;
        }
        else{
            regPhone.setError(null);
            return true;
        }
    }
    private  Boolean validatePassWord()
    {
        String val =regPassword.getEditText().getText().toString();
        String passwordVal="^"+"(?=.*[a-zA-Z])"+"(?=.*[@#$%^&!+=])"+".{4,}"+"$";
        if(val.isEmpty())
        {
            regPassword.setError("field cannot be empty");
            return false;
        }
        else if (!val.matches(passwordVal))
        {
            regPassword.setError("Password is too weak");
            return false;
        }
        else{
            regPassword.setError(null);
            return true;
        }
    }
public void registerUser(View view)


    {
        if(!validatePassWord()| !validatePhoneNO()| !validateEmail()| !validateUsername())
        {

            return;
        }
        String username = regUserName.getEditText().getText().toString();
        Float speedlimit = Float.valueOf(regSpeedLimit.getEditText().getText().toString());
        String phoneNo = regPhone.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        userHelperClass helperClass = new userHelperClass(username, speedlimit, phoneNo, password);
        reference.child(username).setValue(helperClass);
        Intent intent = new Intent(this, login.class);
        //Float clickedItem=helperClass.getspeedlimit();
        //final Intent intent1 = intent.putExtra(String.valueOf(EXTRASPEEDLIMIT), clickedItem.getClass());
        startActivity(intent);

    }
}
