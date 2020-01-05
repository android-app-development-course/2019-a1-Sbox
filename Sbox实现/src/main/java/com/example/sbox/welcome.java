package com.example.sbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    private Button mBtnwel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mBtnwel_button = (Button) findViewById(R.id.welcome_wel);
        mBtnwel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                //跳转到Login
                Intent intent = new Intent(welcome.this,login.class);
                startActivity(intent);

            }
        });
    }

}
