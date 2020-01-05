package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class forget extends AppCompatActivity {
    private Button mBtnforget_button;
    private EditText mEdtforget_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_forget);
        mBtnforget_button=(Button)findViewById(R.id.forget_reset);
        mEdtforget_email=(EditText)findViewById(R.id.forget_email);
        mBtnforget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final String email=mEdtforget_email.getText().toString();
                    BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(forget.this,"重置密码请求成功，请到"+email+"邮箱进行密码重置操作",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(forget.this,login.class);
                                startActivity(intent);
                            }else{
                                Log.e("BMOB",e.toString());
                                Toast.makeText(forget.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
