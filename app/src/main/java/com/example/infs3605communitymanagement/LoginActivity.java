package com.example.infs3605communitymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPwd;
    SharedPreferences sharedPref;
    public String savedLogin;
    public String savedUserID;
    public String savedPassword;
    public boolean savedUserType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // no dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        initView();
    }

    //set screen
    private void initView() {
        Button btn_login = findViewById(R.id.btn_login);
        TextView tv_register = findViewById(R.id.tv_register);
        TextView tv_reset = findViewById(R.id.tv_reset);
        edtName = findViewById(R.id.etAnswer);
        edtPwd = findViewById(R.id.edt_pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                //error text
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length() > 10) {
                    Toast.makeText(LoginActivity.this, "Username must not over 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = edtPwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length() < 6 || pwd.length() > 10) {
                    Toast.makeText(LoginActivity.this, "Password must be within 6-10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(name, pwd);
            }
        });

        //register button
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //forget password
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Forget password")
                        .setMessage("Password is displayed on the text field")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = edtName.getText().toString();
                                if (TextUtils.isEmpty(name)) {
                                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });
    }

    //login and error messages
    private void login(String name, String pwd) {
        sharedPref = getSharedPreferences(savedLogin, MODE_PRIVATE);
        savedPassword=sharedPref.getString(name, "");
        if (savedPassword.contains(pwd) && savedPassword.contains("!w!w!f!")) {
            startActivity(new Intent(this, WWFMainActivity.class));
            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
        } else if (savedPassword.equals(pwd)) {
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
        } else{
            Log.d("savedPassword",savedPassword);
        }
    }
}
