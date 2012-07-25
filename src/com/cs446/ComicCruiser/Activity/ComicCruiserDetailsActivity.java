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
	private static final int COVER_HEIGHT = 250;
	private static final int COVER_WIDTH = 300;
	private String title;
    private View.OnClickListener readPageClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	Intent i = new Intent(ComicCruiserDetailsActivity.this, ComicCruiserReadComicActivity.class);
        	i.putExtra(ComicCruiserReadComicActivity.ISSUE_TITLE_KEY, title);
        	i.putExtra(ComicCruiserLibraryActivity.ISSUE_MODE_KEY, false);
        	startActivity(i);
        }
      };
      private View.OnClickListener readFrameClickHandler = new View.OnClickListener() {
          public void onClick(View v) {
          	Intent i = new Intent(ComicCruiserDetailsActivity.this, ComicCruiserReadComicActivity.class);
          	i.putExtra(ComicCruiserReadComicActivity.ISSUE_TITLE_KEY, title);
          	i.putExtra(ComicCruiserLibraryActivity.ISSUE_MODE_KEY, true);
          	startActivity(i);
          }
        };
      
     private View.OnClickListener deleteClickHandler = new View.OnClickListener() {
          public void onClick(View v) {
          	RepositoryFacade.deleteIssue(RepositoryFacade.getIssueByTitle(title));
          	ComicCruiserDetailsActivity.this.finish();
          }
        };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        
        Button b1 = (Button) findViewById(R.id.detailsNormalButton);
        Button b2 = (Button) findViewById(R.id.detailsFrameButton);
        Button b3 = (Button) findViewById(R.id.detailsDeleteButton);
        b1.setOnClickListener(readPageClickHandler);
        b2.setOnClickListener(readFrameClickHandler);
        b3.setOnClickListener(deleteClickHandler);
        title = ComicCruiserDetailsActivity.this.getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        ((TextView)(findViewById(R.id.detailsTitleView))).setText(title);
        
        Bitmap bm = RepositoryFacade.getIssueByTitle(title).getCoverPage();
        ImageView cover = ((ImageView)(findViewById(R.id.detailsCoverImage)));
        cover.setAdjustViewBounds(true);
        cover.setMaxHeight(COVER_HEIGHT);
        cover.setMaxWidth(COVER_WIDTH);
        cover.setImageBitmap(bm);
    }
}
