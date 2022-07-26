package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;


public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPwd;
    SharedPreferences sharedPref;
    SharedPreferences sharedPrefUser;
    public String savedLogin;
    public String user;
    private Switch swUserType;
    private boolean userType;
    private EditText etFullName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Sign Up");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getWindow().setStatusBarColor(ContextCompat.getColor(RegisterActivity.this,R.color.black));
        initView();

        // Back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        edtName = findViewById(R.id.usernameAnswer);
        edtPwd = findViewById(R.id.edt_pwd);
        swUserType = findViewById(R.id.swUserType);
        etFullName = findViewById(R.id.FullNameInput);
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
                if (swUserType.isChecked()){
                    userType = true;
                }else {
                    userType = false;
                }
                String fullname = etFullName.getText().toString();
                register(name, pwd, fullname,userType);
            }
        });
    }

    //set username & password
    private void register(String name, String pwd,String fullname,boolean userType) {

        TextInputLayout usernameLayout = findViewById(R.id.usernameInput);
        String usernameText = usernameLayout.getEditText().getText().toString();

        TextInputLayout passwordLayout = findViewById(R.id.passwordInput);
        String passwordText = passwordLayout.getEditText().getText().toString();

        sharedPref = getSharedPreferences(savedLogin, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (userType ==true){
            editor.putString(name,pwd+"!w!w!f!");
        } else {
            editor.putString(name,pwd);
        }
        editor.commit();
        //load home page
        Toast.makeText(this, "Register success, loading to home page now", Toast.LENGTH_LONG).show();
        //WWF staff
        if (userType ==true){
            sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
            SharedPreferences.Editor editorUser = sharedPrefUser.edit();
            editorUser.putString("current user",name);
            editorUser.commit();
            Intent intent = new Intent(RegisterActivity.this, UserListActivity.class);
            intent.putExtra("INTENT_USERNAME", usernameText);
            intent.putExtra("INTENT_PASSWORD", passwordText);
            intent.putExtra("INTENT_USERTYPE", "WWF Staff");
            intent.putExtra("INTENT_FULLNAME", fullname);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //Curators
        } else {
            sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
            SharedPreferences.Editor editorUser = sharedPrefUser.edit();
            editorUser.putString("current user",name);
            editorUser.commit();
            Intent intent = new Intent(this, ExperienceActivity.class);
            intent.putExtra("INTENT_USERNAME", usernameText);
            intent.putExtra("INTENT_PASSWORD", passwordText);
            intent.putExtra("INTENT_USERTYPE", "Curator");
            intent.putExtra("INTENT_FULLNAME", fullname);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        finish();
    }

    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
