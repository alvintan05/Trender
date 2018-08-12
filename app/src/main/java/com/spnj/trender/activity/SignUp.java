package com.spnj.trender.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spnj.trender.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button signUpBtn;
    private EditText nama, username, email, password, repassword;

    private FirebaseAuth fAuth;
    private DatabaseReference mUserDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up ...");

        signUpBtn = (Button) findViewById(R.id.btn_createAcc);
        nama = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.txtPass);
        repassword = (EditText) findViewById(R.id.txtRePass);

        fAuth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_createAcc:
                String fullname = nama.getText().toString().trim();
                String uname = username.getText().toString().trim();
                String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();
                String urepassword = repassword.getText().toString().trim();

                registerUser(fullname, uname, uemail, upassword, urepassword);
                break;
        }
    }

    private void registerUser(final String fullname, final String uname, final String uemail, String upassword, String urepassword) {

        progressDialog.show();

        fAuth.createUserWithEmailAndPassword(uemail, upassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            DatabaseReference newUser = mUserDatabase.child(fAuth.getCurrentUser().getUid());
                            newUser.child("nama").setValue(fullname);
                            newUser.child("username").setValue(uname);
                            newUser.child("email").setValue(uemail);

                            progressDialog.dismiss();
                            startActivity(new Intent(SignUp.this, HomeActivity.class));
                            finish();
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
