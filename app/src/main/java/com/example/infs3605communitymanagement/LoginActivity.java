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
import android.widget.ImageButton;
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
    SharedPreferences sharedPrefUser;
    public String user;
    public String savedLogin;
    public String savedPassword;
    public Button tvRegister;
    public TextView tvReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();
        //getSupportActionBar().setTitle("WWF Community Management");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.colorAccent));
        // no dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        initView();
    }


    //set screen
    private void initView() {

        Button btn_login = findViewById(R.id.btn_login);
        ImageButton signInWithGoogle = findViewById(R.id.googleButton);
        tvReset = findViewById(R.id.tv_reset);
        edtName = findViewById(R.id.usernameAnswer);
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
        tvRegister = findViewById(R.id.btnRegisterNew);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View detailView) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //forget password
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Feature not included in the demo", Toast.LENGTH_LONG).show();
            }
        });
        //Login with Google
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"This feature will be implemented soon, stay tuned for updates!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //login and error messages
    private void login(String name, String pwd) {
        sharedPref = getSharedPreferences(savedLogin, MODE_PRIVATE);
        savedPassword=sharedPref.getString(name, "");
        sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPrefUser.edit();
        if (savedPassword.contains(pwd) && savedPassword.contains("!w!w!f!")) {
            editorUser.putString("current user",name);
            editorUser.commit();
            Intent intent = new Intent(this, WWFMainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
        } else if (savedPassword.equals(pwd)) {
            editorUser.putString("current user",name);
            Log.d("username", name);
            editorUser.commit();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.username, name);
            startActivity(intent);
            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
        }
    }
}
