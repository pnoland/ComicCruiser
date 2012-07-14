package com.cs446.ComicCruiser.Activity;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class ComicCruiserDetailsActivity extends Activity {
	private String title;
    View.OnClickListener readClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	Intent i = new Intent(ComicCruiserDetailsActivity.this, ComicCruiserReadComicActivity.class);
        	String title = ComicCruiserDetailsActivity.this.getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        	i.putExtra(ComicCruiserReadComicActivity.ISSUE_TITLE_KEY, title);
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
        String title = ComicCruiserDetailsActivity.this.getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        ((TextView)(findViewById(R.id.detailsTitleView))).setText(title);
        
        Bitmap bm = RepositoryFacade.getIssueByTitle(title).getCoverPage();
        ((ImageView)(findViewById(R.id.detailsCoverImage))).setImageBitmap(bm);
    }
}
