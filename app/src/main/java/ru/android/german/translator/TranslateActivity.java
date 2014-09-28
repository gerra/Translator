package ru.android.german.translator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by german on 27.09.14.
 */
public class TranslateActivity extends Activity {
    private Button backButton;
    private GridViewAdapter adapter = null;
    ArrayList<Bitmap> data = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.translate_activity);
        Intent intent = getIntent();
        adapter = new GridViewAdapter(this, R.layout.grid_item, data);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (intent != null) {
            String translate = intent.getStringExtra("translate");
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(translate);
            for (int i = 0; i < 9; i++) {
                String url;
                if (intent.hasExtra("img" + i)) {
                    url = intent.getStringExtra("img"+i);
                    new LoadImage().execute(url);
                }
            }
        }
//        new LoadImage().execute("http://tabletpcssource.com/wp-content/uploads/2011/05/android-logo.png");
//        new LoadImage().execute("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg");
//        new LoadImage().execute("http://simpozia.com/pages/images/stories/windows-icon.png");
//        new LoadImage().execute("http://radiotray.sourceforge.net/radio.png");
//        new LoadImage().execute("http://www.bandwidthblog.com/wp-content/uploads/2011/11/twitter-logo.png");
//        new LoadImage().execute("http://weloveicons.s3.amazonaws.com/icons/100907_itunes1.png");
//        new LoadImage().execute("http://weloveicons.s3.amazonaws.com/icons/100929_applications.png");
//        new LoadImage().execute("http://www.idyllicmusic.com/index_files/get_apple-iphone.png");
//        new LoadImage().execute("http://www.frenchrevolutionfood.com/wp-content/uploads/2009/04/Twitter-Bird.png");
//        new LoadImage().execute("http://www.desiredsoft.com/images/icon_webhosting.png");
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                data.add(image);
                adapter.notifyDataSetChanged();
                Toast.makeText(TranslateActivity.this, "Download went okay ", Toast.LENGTH_SHORT).show();
            }else{
                data.add(BitmapFactory.decodeResource(getResources(), R.drawable.android_logo));
                adapter.notifyDataSetChanged();
                Toast.makeText(TranslateActivity.this, "Image Does not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
