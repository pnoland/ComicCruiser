package com.cs446.ComicCruiser.Activity;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.ImageIterator;
import com.cs446.ComicCruiser.ComicRepository.Issue;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.HorizontalPager;

import android.app.Activity;
import android.os.Bundle;

public class ComicCruiserReadComicActivity extends Activity {
    protected static final String ISSUE_TITLE_KEY = "com.cs446.ComicCruiser.issue_title_key";

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_comic_view);
        //TouchImageView imageView = (TouchImageView)((findViewById(R.id.readComicImage)));
        HorizontalPager pager = (HorizontalPager) ((findViewById(R.id.readComicImage)));
        
        String title = getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        boolean mode = getIntent().getBooleanExtra(ComicCruiserLibraryActivity.ISSUE_MODE_KEY, false);
        Issue issue = RepositoryFacade.getIssueByTitle(title);
        if(mode && !issue.hasFrameData()){
        	issue.fetchFrameData();
        }
        ImageIterator iterator = RepositoryFacade.openIssueForReading(issue, mode);
        pager.setImageIterator(iterator);
        pager.initialize();
    }
    
}
