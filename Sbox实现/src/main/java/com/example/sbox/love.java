package com.example.sbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class love extends AppCompatActivity {

    private RecyclerView mrlike;
    private FloatingActionButton mfab_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        mfab_refresh=(FloatingActionButton)findViewById(R.id.fab_refresh);
        mfab_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Post> query=new BmobQuery<Post>();
                User user= BmobUser.getCurrentUser(User.class);
                query.addWhereRelatedTo("likes",new BmobPointer(user));
                query.findObjects(new FindListener<Post>() {
                    @Override
                    public void done(List<Post> list, final BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "查询个数：" + list.size());
                            final List<Post> posts = new ArrayList<>();
                            posts.addAll(list);
                            LikeAdapter likeAdapter = new LikeAdapter(posts);
                            mrlike.setAdapter(likeAdapter);

                            likeAdapter.setOnItemClickListener(new LikeAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(love.this, ourpost.class);
                                    Bundle b = new Bundle();
                                    b.putInt("position", (Integer) position);
                                    b.putString("objectId", posts.get(position).getObjectId());
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }
                            });

                            likeAdapter.setLongClickItemLisenter(new LikeAdapter.LongClickItemListener() {
                                @Override
                                public void LongClickListener(final int position) {
                                    AlertDialog.Builder k1 = new AlertDialog.Builder(love.this);
                                    k1.setTitle("删除提示");
                                    k1.setMessage("确认删除？");
                                    k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            User user=BmobUser.getCurrentUser(User.class);
                                            BmobRelation relation=new BmobRelation();
                                            Post post = posts.get(position);
                                            relation.remove(post);
                                            user.setLikes(relation);
                                            user.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if(e==null){
                                                        Log.i("bmob","已从我喜欢中删除");
                                                        Toast.makeText(love.this,"已删除",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                    k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(love.this, "删除失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    k1.show();
                                }
                            });
                        }else {
                            Log.i("bmob", "失败：" + e.getMessage());
                        }
                    }
                });
            }
        });
        mrlike=(RecyclerView)findViewById(R.id.LIKE);
        mrlike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        BmobQuery<Post> query=new BmobQuery<Post>();
        User user= BmobUser.getCurrentUser(User.class);
        query.addWhereRelatedTo("likes",new BmobPointer(user));
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, final BmobException e) {
                if (e == null) {
                    Log.i("bmob", "查询个数：" + list.size());
                    final List<Post> posts = new ArrayList<>();
                    posts.addAll(list);
                    LikeAdapter likeAdapter = new LikeAdapter(posts);
                    mrlike.setAdapter(likeAdapter);

                    likeAdapter.setOnItemClickListener(new LikeAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Intent intent = new Intent(love.this, ourpost.class);
                            Bundle b = new Bundle();
                            b.putInt("position", (Integer) position);
                            b.putString("objectId", posts.get(position).getObjectId());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });

                    likeAdapter.setLongClickItemLisenter(new LikeAdapter.LongClickItemListener() {
                        @Override
                        public void LongClickListener(final int position) {
                            AlertDialog.Builder k1 = new AlertDialog.Builder(love.this);
                            k1.setTitle("删除提示");
                            k1.setMessage("确认删除？");
                            k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    User user=BmobUser.getCurrentUser(User.class);
                                    BmobRelation relation=new BmobRelation();
                                    Post post = posts.get(position);
                                    relation.remove(post);
                                    user.setLikes(relation);
                                    user.update(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if(e==null){
                                                Log.i("bmob","已从我喜欢中删除");
                                                Toast.makeText(love.this,"已删除",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                            k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(love.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                            k1.show();
                        }
                    });
                }else {
                            Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });
    }
}
