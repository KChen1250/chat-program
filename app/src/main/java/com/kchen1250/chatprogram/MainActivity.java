package com.kchen1250.chatprogram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText message;
    private EditText url;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        name = (EditText) findViewById(R.id.name);
        message = (EditText) findViewById(R.id.message);
        url = (EditText) findViewById(R.id.url);
        image = (ImageView)findViewById(R.id.image);

        url.addTextChangedListener(textWatcher);

        findViewById(R.id.linear_layout).requestFocus();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            try {
                URL u = new URL(url.getText().toString());
                Bitmap bmp = BitmapFactory.decodeStream(u.openConnection().getInputStream());
                image.setImageBitmap(bmp);
            } catch (Exception e) {
                e.printStackTrace();
                image.setImageDrawable(null);
            }
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    public void next(View view) {
        Intent intent = new Intent(this, Display.class);
        intent.putExtra("NAME", name.getText().toString());
        intent.putExtra("MESSAGE", message.getText().toString());
        intent.putExtra("URL", url.getText().toString());
        startActivity(intent);
    }
}
