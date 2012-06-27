package com.cs446.ComicCruiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComicCruiserDetailsActivity extends Activity {
    View.OnClickListener readClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	Intent i = new Intent(ComicCruiserDetailsActivity.this, ComicCruiserReadComicActivity.class);
        	startActivity(i);
        }
      };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        
        Button b1 = (Button) findViewById(R.id.detailsNormalButton);
        Button b2 = (Button) findViewById(R.id.detailsFrameButton);
        b1.setOnClickListener(readClickHandler);
        b2.setOnClickListener(readClickHandler);
        
    }
}
