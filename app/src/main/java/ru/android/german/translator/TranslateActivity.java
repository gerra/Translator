package ru.android.german.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by german on 27.09.14.
 */
public class TranslateActivity extends Activity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_activity);

        String[] values = new String[] { "1", "2", "3",
                "4", "5", "6", "7", "8", "9", "10" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
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
