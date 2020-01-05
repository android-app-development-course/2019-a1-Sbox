package com.example.sbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class mine extends AppCompatActivity {
    private ImageButton mBtnmine_button;
    private RecyclerView mRview;
    private ImageButton mBtnmine_fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_mine);
        mRview=(RecyclerView)findViewById(R.id.POSTVIEW);
        mRview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if(BmobUser.isLogin()){
            BmobQuery<Post> query = new BmobQuery<>();
            query.addWhereEqualTo("author",BmobUser.getCurrentUser(User.class));
            query.order("-updatedAt");
            query.include("author");
            query.findObjects(new FindListener<Post>() {
                @Override
                public void done(List<Post> list, BmobException e) {
                    if(e==null){
                        final List<Post> posts=new ArrayList<>();
                        posts.addAll(list);
                        MyAdapter myAdapter=new MyAdapter(posts);
                        mRview.setAdapter(myAdapter);

                        myAdapter.setLongClickLisenter(new MyAdapter.LongClickLisenter() {
                            @Override
                            public void LongClickListenter(final int position) {
                                AlertDialog.Builder k1 = new AlertDialog.Builder(mine.this);
                                k1.setTitle("删除提示");
                                k1.setMessage("确认删除？");
                                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Post post = posts.get(position);
                                        post.delete(post.getObjectId(), new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    Toast.makeText(mine.this, "删除成功,刷新试试", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Log.e("BMOB", e.toString());
                                                    Toast.makeText(mine.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mine.this, "删除失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                k1.show();
                            }
                        });
                    }else{
                        Log.e("BMOB", e.toString());
                        Toast.makeText(mine.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            Toast.makeText(mine.this,"请先登陆",Toast.LENGTH_LONG).show();
        }
        mBtnmine_button= (ImageButton) findViewById(R.id.mine_add);
        mBtnmine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mine.this, add.class);
                startActivity(intent);
            }
        });
        mBtnmine_fresh=(ImageButton)findViewById(R.id.mine_fresh);
        mBtnmine_fresh.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(BmobUser.isLogin()){
                    BmobQuery<Post> query = new BmobQuery<>();
                    query.addWhereEqualTo("author",BmobUser.getCurrentUser(User.class));
                    query.order("-updatedAt");
                    query.include("author");
                    query.findObjects(new FindListener<Post>() {
                        @Override
                        public void done(List<Post> list, BmobException e) {
                            if (e == null) {
                                final List<Post> posts = new ArrayList<>();
                                posts.addAll(list);
                                MyAdapter myAdapter = new MyAdapter(posts);
                                mRview.setAdapter(myAdapter);

                                myAdapter.setLongClickLisenter(new MyAdapter.LongClickLisenter() {
                                    @Override
                                    public void LongClickListenter(final int position) {
                                        AlertDialog.Builder k1 = new AlertDialog.Builder(mine.this);
                                        k1.setTitle("删除提示");
                                        k1.setMessage("确认删除？");
                                        k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Post post = posts.get(position);
                                                post.delete(post.getObjectId(), new UpdateListener() {
                                                    @Override
                                                    public void done(BmobException e) {
                                                        if (e == null) {
                                                            Toast.makeText(mine.this, "删除成功,刷新试试", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Log.e("BMOB", e.toString());
                                                            Toast.makeText(mine.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                        k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(mine.this, "删除失败", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        k1.show();
                                    }
                                });
                            }
                        }
                    });
                }else{
                    Toast.makeText(mine.this,"请先登陆",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
