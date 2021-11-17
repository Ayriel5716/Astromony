package com.interview.cmoney;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ThirdPage extends AppCompatActivity {
    private TextView date,title,copyright,credit_copyright,description;
    private ImageView hdurl;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_page);

        date = (TextView) findViewById(R.id.date);
        hdurl = (ImageView) findViewById(R.id.hdurl);
        title = (TextView) findViewById(R.id.topic);
        copyright = (TextView) findViewById(R.id.copyright);
        credit_copyright = (TextView)findViewById(R.id.credit_copyright);
        description = (TextView) findViewById(R.id.article);

        ProgressDialog dialog = ProgressDialog.show(this, "請稍等",
                "載入資料中", true);
        new Thread(() -> {
            credit_copyright.setVisibility(View.INVISIBLE);
//        Receive data from 2nd page
            Intent intent = getIntent();
            String Date = intent.getExtras().getString("Date");
            String Hdurl = intent.getExtras().getString("Hdurl");
            String Title = intent.getExtras().getString("Title");
            String Copyright = intent.getExtras().getString("Copyright");
            String Description = intent.getExtras().getString("Description");
            try {
                bitmap = new GetImage(hdurl).execute(Hdurl).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            runOnUiThread(() -> {
                dialog.dismiss();
                credit_copyright.setVisibility(View.VISIBLE);
                date.setText(Date);
                hdurl.setImageBitmap(bitmap);
                title.setText(Title);
                copyright.setText(Copyright);
                description.setText(Description);
            });
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
