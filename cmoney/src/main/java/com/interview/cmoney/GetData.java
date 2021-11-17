package com.interview.cmoney;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.interview.cmoney.SecondPage.jsonData;

public class GetData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        String ans;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                Log.d("HTTP", line);
                sb.append(line);
                line = bufferedReader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ans = sb.toString();
        return ans;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
