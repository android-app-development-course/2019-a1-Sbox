package com.example.sbox;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 胡倩溶 on 2019/12/23.
 */
public class User extends BmobUser{
    private BmobRelation likes;

    public BmobRelation getLikes(){
        return likes;
    }

    public User setLikes(BmobRelation likes){
        this.likes=likes;
        return this;
    }
}
