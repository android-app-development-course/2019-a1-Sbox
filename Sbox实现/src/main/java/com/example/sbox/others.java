package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class others extends AppCompatActivity {
    private RecyclerView mrview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_others);
        mrview=(RecyclerView)findViewById(R.id.ourspost);
        mrview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if(BmobUser.isLogin()){
            BmobQuery<Post> query = new BmobQuery<>();
            query.addWhereNotEqualTo("author",BmobUser.getCurrentUser(User.class));
            query.order("-updatedAt");
            query.include("author");
            query.findObjects(new FindListener<Post>() {
                @Override
                public void done(final List<Post> list, BmobException e) {
                    if (e == null) {
                        final List<Post> posts = new ArrayList<>();
                        posts.addAll(list);
                        final OurAdapter myAdapter = new OurAdapter(posts);
                        mrview.setAdapter(myAdapter);

                        myAdapter.setOnItemClickListener(new OurAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                Intent intent = new Intent(others.this, ourpost.class);
                                Bundle b = new Bundle();
                                b.putInt("position", (Integer) position);
                                b.putString("objectId", posts.get(position).getObjectId());
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        });
                    }else{
                        Log.e("BMOB", e.toString());
                        Toast.makeText(others.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(others.this,"请先登陆",Toast.LENGTH_LONG).show();
        }
    }
}
