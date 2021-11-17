package com.interview.cmoney;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SecondPage extends AppCompatActivity {

    public static String jsonData = "https://raw.githubusercontent.com/cmmobile/NasaDataSet/main/apod.json";

    List<Astromony> aData = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        loadingData();
    }


    private void loadingData() {

        ProgressDialog dialog = ProgressDialog.show(this, "請稍等",
                "載入資料中", true);
        new Thread(() -> {
            try {
                String jsonOpendata = new GetData().execute(jsonData).get();

                JSONArray jsonArray = new JSONArray(jsonOpendata);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Astromony astro = new Astromony();
                    astro.setDescription(jsonObject.getString("description"));
                    astro.setCopyright(jsonObject.getString("copyright"));
                    astro.setTitle(jsonObject.getString("title"));
                    astro.setUrl(jsonObject.getString("url"));
                    astro.setDate(jsonObject.getString("date"));
                    astro.setHdurl(jsonObject.getString("hdurl"));

                    aData.add(astro);

                }

                runOnUiThread(() -> {
                    dialog.dismiss();
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, aData);
                    recyclerView.setAdapter(recyclerAdapter);
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private Context context;
        private List<Astromony> aData;

        public RecyclerAdapter(Context context, List<Astromony> aData) {
            this.context = context;
            this.aData = aData;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView title;
            CardView cardView;

            public ViewHolder(@NonNull View view) {
                super(view);

                title = view.findViewById(R.id.titles);
                image = view.findViewById(R.id.images);
                cardView = view.findViewById(R.id.card);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
            holder.title.setText(aData.get(position).getTitle());
            ImageView i = holder.image;
            new GetImage(i).execute(aData.get(position).getUrl());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, ThirdPage.class);
                    intent.putExtra("Date", aData.get(position).getDate());
                    intent.putExtra("Hdurl", aData.get(position).getHdurl());
                    intent.putExtra("Title", aData.get(position).getTitle());
                    intent.putExtra("Copyright", aData.get(position).getCopyright());
                    intent.putExtra("Description", aData.get(position).getDescription());
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return aData.size();
        }
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



