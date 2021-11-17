package com.interview.cmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity implements View.OnClickListener {

    private Button requestBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        setupViewComponent();
    }

    private void setupViewComponent() {
        requestBtn = (Button)findViewById(R.id.f_b001);
        requestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent();
        intent.setClass(FirstPage.this,SecondPage.class);
        startActivity(intent);
    }
}