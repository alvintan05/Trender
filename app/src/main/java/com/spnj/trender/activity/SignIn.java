package com.spnj.trender.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spnj.trender.R;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private Button signInBtn;
    private EditText email, password;
    private ProgressDialog progressDialog;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        fAuth = FirebaseAuth.getInstance();

        signInBtn = (Button) findViewById(R.id.btn_signIn);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_pass);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In ...");

        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signIn:

                String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();

                if (!TextUtils.isEmpty(uemail) && !TextUtils.isEmpty(upassword)) {
                    userLogin(uemail, upassword);
                }
                break;
        }
    }

    private void userLogin(String uemail, String upassword) {
        progressDialog.show();

        fAuth.signInWithEmailAndPassword(uemail, upassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignIn.this, HomeActivity.class));
                            Toast.makeText(SignIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
