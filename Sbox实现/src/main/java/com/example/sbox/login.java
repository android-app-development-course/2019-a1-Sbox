package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class login extends AppCompatActivity {
    private Button mBtnlogin_button1;
    private Button mBtnlogin_button2;
    private Button mBtnlogin_button3;
    private EditText mEdtlogin_user;
    private EditText mEdtlogin_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_login);
        mEdtlogin_user=(EditText)findViewById(R.id.login_user);
        mEdtlogin_password=(EditText)findViewById(R.id.login_password);
        mBtnlogin_button1=(Button)findViewById(R.id.login_login);
        mBtnlogin_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String user_num=mEdtlogin_user.getText().toString();
                    String user_password=mEdtlogin_password.getText().toString().trim();
                    if(user_num.isEmpty()||user_password.isEmpty()){
                        Toast.makeText(login.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final User user=new User();
                    user.setUsername(user_num);
                    user.setPassword(user_password);
                    user.login(new SaveListener<User>() {
                        @Override
                        public void done(User bombuser, BmobException e) {
                            if(e==null){
                                User user= BmobUser.getCurrentUser(User.class);
                                Toast.makeText(login .this, "登陆成功：" + user.getUsername(), Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(login.this,homepage.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(login.this,"登陆失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        mBtnlogin_button2=(Button)findViewById(R.id.login_signup);
        mBtnlogin_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到sign_up
                Intent intent = new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });
        mBtnlogin_button3=(Button)findViewById(R.id.login_forget);
        mBtnlogin_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到forget
                Intent intent = new Intent(login.this,forget.class);
                startActivity(intent);
            }
        });
    }
}
