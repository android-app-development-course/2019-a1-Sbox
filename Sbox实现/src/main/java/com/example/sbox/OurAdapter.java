package com.example.sbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 胡倩溶 on 2020/1/4.
 */
public class OurAdapter<T> extends RecyclerView.Adapter<OurAdapter.VH>{

    private List<Post> posts;
    private OnItemClickListener onItemClickListener;

    public OurAdapter(List<Post> postList){
        this.posts=postList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ourpost_item,parent,false);
        final VH vh=new VH(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position=vh.getLayoutPosition();
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Post post=posts.get(position);
        holder.ourposttitle.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class VH extends RecyclerView.ViewHolder{

        TextView ourposttitle;

        public VH(@NonNull View itemview){
            super(itemview);

            ourposttitle=itemview.findViewById(R.id.ourpostTitle);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}

