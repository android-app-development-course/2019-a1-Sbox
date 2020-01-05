package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class sign_up extends AppCompatActivity {
    private Button mBtnsignup_button;
    private EditText mEdtsignup_user;
    private EditText mEdtsignup_password;
    private EditText mEdtsignup_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_sign_up);
        mEdtsignup_user=(EditText)findViewById(R.id.signup_user);
        mEdtsignup_password=(EditText)findViewById(R.id.signup_password);
        mEdtsignup_email=(EditText)findViewById(R.id.signup_email);
        mBtnsignup_button = (Button) findViewById(R.id.signup_signup);
        mBtnsignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String user_num=mEdtsignup_user.getText().toString();
                    String user_password=mEdtsignup_password.getText().toString().trim();
                    String user_email=mEdtsignup_email.getText().toString();
                    if(user_num.isEmpty()||user_password.isEmpty()||user_email.isEmpty()){
                        Toast.makeText(getApplication(), "账号、邮箱或密码不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    User myUser=new User();
                    myUser.setUsername(user_num);
                    myUser.setPassword(user_password);
                    myUser.setEmail(user_email);
                    myUser.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null) {
                                Toast.makeText(sign_up.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(sign_up.this, homepage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(sign_up.this, "注册失败,用户名可能重复 ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    finish();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
