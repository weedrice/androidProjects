package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

import java.net.HttpURLConnection;
import java.net.URL;

public class ShowDetailPhoto extends AppCompatActivity {
    PhotoView photoView;
    String uri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_photo);

        photoView = (PhotoView)findViewById(R.id.detailPhoto);
        Intent intent = getIntent();
        uri = intent.getExtras().getString("url");

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        try {
            thread.join();

            photoView.setImageBitmap(bitmap);
        } catch(Exception e) {
            e.printStackTrace();
        }

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
