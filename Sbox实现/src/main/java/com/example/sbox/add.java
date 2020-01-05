package com.example.sbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class add extends AppCompatActivity {
    private ImageButton mBtnadd_button1;
    private ImageButton mBtnadd_button2;
    private ImageButton mBtnadd_button3;
    private ImageButton mBtnadd_button4;
    private ImageButton mBtnadd_button5;
    private Button mBtnadd_ok;
    private EditText mEdtadd_content;
    private EditText mEdtadd_title;
    private int mimageout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_add);
        mBtnadd_button1=(ImageButton) findViewById(R.id.add_treasure1);
        mBtnadd_button2=(ImageButton) findViewById(R.id.add_treasure2);
        mBtnadd_button3=(ImageButton) findViewById(R.id.add_treasure3);
        mBtnadd_button4=(ImageButton) findViewById(R.id.add_treasure4);
        mBtnadd_button5=(ImageButton) findViewById(R.id.add_treasure5);
        mBtnadd_ok=(Button) findViewById(R.id.add_ok);
        mEdtadd_content=(EditText) findViewById(R.id.add_content);
        mEdtadd_title=(EditText) findViewById(R.id.add_title);
        mBtnadd_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(add.this);
                k1.setTitle("选择提示");
                k1.setMessage("是否选择该图标作为外表？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择成功", Toast.LENGTH_SHORT).show();
                        mimageout=1;
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnadd_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(add.this);
                k1.setTitle("选择提示");
                k1.setMessage("是否选择该图标作为外表？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择成功", Toast.LENGTH_SHORT).show();
                        mimageout=2;
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnadd_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(add.this);
                k1.setTitle("选择提示");
                k1.setMessage("是否选择该图标作为外表？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择成功", Toast.LENGTH_SHORT).show();
                        mimageout=3;
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnadd_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(add.this);
                k1.setTitle("选择提示");
                k1.setMessage("是否选择该图标作为外表？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择成功", Toast.LENGTH_SHORT).show();
                        mimageout=4;
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnadd_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(add.this);
                k1.setTitle("选择提示");
                k1.setMessage("是否选择该图标作为外表？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择成功", Toast.LENGTH_SHORT).show();
                        mimageout=5;
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(add.this, "选择失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnadd_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int imageout=mimageout;
                String content = mEdtadd_content.getText().toString();
                String title=mEdtadd_title.getText().toString();
                if (BmobUser.isLogin()) {
                    final Post post = new Post();
                    post.setImageOUT(imageout);
                    post.setTitle(title);
                    post.setContent(content);
                    post.setAuthor(BmobUser.getCurrentUser(User.class));
                    post.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(add.this, "发布帖子成功:" + s, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("BMOB", e.toString());
                                Toast.makeText(add.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(add.this, "请先登陆", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
