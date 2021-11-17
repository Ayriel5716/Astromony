package com.interview.cmoney;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class GetImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    Bitmap bitmap;

    public GetImage(ImageView image) {
        this.imageView = image;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String imgUrl = url[0];
        bitmap = null;
        try {
            InputStream inputStream = new java.net.URL(imgUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
