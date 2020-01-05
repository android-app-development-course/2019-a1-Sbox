package com.example.sbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 胡倩溶 on 2019/12/31.
 */
public class MyAdapter<T> extends RecyclerView.Adapter<MyAdapter.VH>{

    private List<Post> posts;
    private LongClickLisenter longClickLisenter;

    public MyAdapter(List<Post> postList){
        this.posts=postList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        final VH vh=new VH(view);

        view.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                int position=vh.getLayoutPosition();
                if(longClickLisenter!=null){
                    longClickLisenter.LongClickListenter(position);
                }
                return true;
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Post post=posts.get(position);
        holder.posttitle.setText(post.getTitle());
        holder.postcontent.setText(post.getContent());
        holder.postimage.setImageResource(post.outimage());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class VH extends RecyclerView.ViewHolder{

        TextView posttitle;
        TextView postcontent;
        ImageView postimage;

        public VH(@NonNull View itemview){
            super(itemview);

            posttitle=itemview.findViewById(R.id.postTitle);
            postcontent=itemview.findViewById(R.id.postContent);
            postimage=itemview.findViewById(R.id.postImage);
        }
    }

    public interface LongClickLisenter{
        void LongClickListenter(int position);
    }

    public void setLongClickLisenter(LongClickLisenter longClickLisenter){
        this.longClickLisenter=longClickLisenter;
    }
}