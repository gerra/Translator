package ru.android.german.translator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by german on 27.09.14.
 */
public class TranslateActivity extends Activity {
    private Button backButton;
    ArrayList<Bitmap> data = new ArrayList<Bitmap>();
    GridViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_activity);

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
    }
}
