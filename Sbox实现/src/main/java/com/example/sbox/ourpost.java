package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ourpost extends AppCompatActivity {

    private Toolbar mtoolbar;
    private RecyclerView mrdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "608e911a45565a3c9e5370dd80fdbbc3");
        setContentView(R.layout.activity_ourpost);
        mtoolbar=(Toolbar)findViewById(R.id.TOOLBAR);
        setSupportActionBar(mtoolbar);
        mrdetails=(RecyclerView)findViewById(R.id.DETAILS);
        mrdetails.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Bundle b=getIntent().getExtras();
        String objectId=b.getString("objectId");
        BmobQuery<Post> query=new BmobQuery<>();
        query.addWhereEqualTo("objectId", objectId);
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                    List<Post> posts = new ArrayList<>();
                    posts.addAll(list);
                    MyAdapter myAdapter = new MyAdapter(posts);
                    mrdetails.setAdapter(myAdapter);
                } else {
                    Log.e("BMOB", e.toString());
                    Toast.makeText(ourpost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.usemenu, menu);
        return true;
    }

    public  boolean onOptionsItemSelected(MenuItem item){
        int id=R.id.zan;
        User user= BmobUser.getCurrentUser(User.class);
        Post post=new Post();
        Bundle b=getIntent().getExtras();
        String objectId=b.getString("objectId");
        post.setObjectId(objectId);
        BmobRelation relation=new BmobRelation();
        relation.add(post);
        user.setLikes(relation);
        user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Log.i("bmob", "多对多关联添加成功");
                        Toast.makeText(ourpost.this,"已添加到我喜欢",Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("bmob", "失败" + e.getMessage());
                    }
                }
            });
        return true;
    }
}
