package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {
    private Button mBtnhomepage_button1;
    private Button mBtnhomepage_button3;
    private Button mBtnhomepage_button4;
    private Button mBtnhomepage_button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mBtnhomepage_button1=(Button)findViewById(R.id.home_find);
        mBtnhomepage_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到others
                Intent intent = new Intent(homepage.this,others.class);
                startActivity(intent);
            }
        });
        mBtnhomepage_button3=(Button)findViewById(R.id.home_shop);
        mBtnhomepage_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到shop
                Intent intent = new Intent(homepage.this,shop.class);
                startActivity(intent);
            }
        });
        mBtnhomepage_button4=(Button)findViewById(R.id.home_mine);
        mBtnhomepage_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到mine
                Intent intent = new Intent(homepage.this,mine.class);
                startActivity(intent);

            }
        });
        mBtnhomepage_button5=(Button) findViewById(R.id.home_like);
        mBtnhomepage_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepage.this,love.class);
                startActivity(intent);
            }
        });
    }
}
