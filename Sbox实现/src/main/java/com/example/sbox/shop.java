package com.example.sbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class shop extends AppCompatActivity {
    private Button mBtnshop_button1;
    private Button mBtnshop_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mBtnshop_button1=(Button)findViewById(R.id.shop_goods1);
        mBtnshop_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k1 = new AlertDialog.Builder(shop.this);
                k1.setTitle("购买提示");
                k1.setMessage("金额：1元，确认购买？");
                k1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(shop.this, "购买成功", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(shop.this, "购买失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k1.show();
            }
        });
        mBtnshop_button2=(Button)findViewById(R.id.shop_goods2);
        mBtnshop_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder k2 = new AlertDialog.Builder(shop.this);
                k2.setTitle("购买提示");
                k2.setMessage("金额：3元，确认购买？");
                k2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(shop.this, "购买成功", Toast.LENGTH_SHORT).show();
                    }
                });
                k2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(shop.this, "购买失败", Toast.LENGTH_SHORT).show();
                    }
                });
                k2.show();
            }
        });
    }
}
