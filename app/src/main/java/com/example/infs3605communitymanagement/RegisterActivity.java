package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPwd;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        // Back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        edtName = findViewById(R.id.etAnswer);
        edtPwd = findViewById(R.id.edt_pwd);
        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            //error messages
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length() > 10) {
                    Toast.makeText(RegisterActivity.this, "Username must not over 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = edtPwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length() < 6 || pwd.length() > 10) {
                    Toast.makeText(RegisterActivity.this, "Password must be within 6-10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                register(name, pwd);
            }
        });
    }

    //username check
    private void register(String name, String pwd) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(name,pwd);
        //load home page
        Toast.makeText(this, "Register success, loading to home page now", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
