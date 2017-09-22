package com.kchen1250.chatprogram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class Display extends AppCompatActivity {

    private String name;
    private String message;
    private String url;
    private TextView message_display;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        message = intent.getStringExtra("MESSAGE");
        url = intent.getStringExtra("URL");
        System.out.println(name + message);
        setTitle("Message from "  + name);

        message_display = (TextView) findViewById(R.id.message);
        message_display.setText(message);

        image = (ImageView) findViewById(R.id.image);
        try {
            URL u = new URL(url);
            Bitmap bmp = BitmapFactory.decodeStream(u.openConnection().getInputStream());
            image.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void exit(View view) {
        finishAffinity();
        System.exit(0);
    }
}
