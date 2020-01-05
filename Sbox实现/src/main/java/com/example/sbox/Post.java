package com.example.sbox;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 胡倩溶 on 2019/12/24.
 */
public class Post extends BmobObject {

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发布者
     */
    private User author;
    /**
     * 图片
     */
    private int imageOUT;
    private BmobFile imageIN;


    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public int getImageOUT() {
        return imageOUT;
    }

    public int outimage(){
        int resId = 0;
        int i=getImageOUT();
        switch(i){
            case 1:
                resId=R.drawable.wear1;
                break;
            case 2:
                resId=R.drawable.wear2;
                break;
            case 3:
                resId=R.drawable.wear3;
                break;
            case 4:
                resId=R.drawable.wear4;
                break;
            case 5:
                resId=R.drawable.wear5;
                break;
            default:
                break;
        }
        return resId;
    }

    public Post setImageOUT(int imageout) {
        this.imageOUT = imageout;
        return this;
    }

    public BmobFile getImageIN() {
        return imageIN;
    }

    public Post setImageIN(BmobFile image) {
        this.imageIN = image;
        return this;
    }
}