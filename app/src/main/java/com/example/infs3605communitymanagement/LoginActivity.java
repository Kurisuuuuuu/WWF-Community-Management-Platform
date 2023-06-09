package com.example.infs3605communitymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.UserDatabase;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

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
    private UserDatabase mUserDb;
    public User currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //dark mode
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        TextInputLayout pwdInput = findViewById(R.id.textInputPassword);
        pwdInput.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);

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
        //database
        mUserDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();
        final boolean[] runCheck = {false};
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                currentUser = mUserDb.userDao().getUserByUsername(name);
                Log.d("current user", String.valueOf(currentUser));
                runCheck[0] = true;
            }
        });
        sharedPref = getSharedPreferences(savedLogin, MODE_PRIVATE);
        savedPassword=sharedPref.getString(name, "");
        sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPrefUser.edit();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (currentUser==null){
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                    } else {
                        if (currentUser.getPassword().contains(pwd) && savedPassword.contains("!w!w!f!")) {
                            editorUser.putString("current user",name);
                            editorUser.commit();
                            Intent intent = new Intent(LoginActivity.this, WWFMainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_LONG).show();
                        } else if (currentUser.getPassword().equals(pwd)) {
                            editorUser.putString("current user",name);
                            Log.d("username", name);
                            editorUser.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.username, name);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }, 100);
        }
}
