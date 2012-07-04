package com.cs446.ComicCruiser;

import com.cs446.ComicCruiser.ComicRepository.ImageIterator;
import com.cs446.ComicCruiser.ComicRepository.Issue;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class ComicCruiserReadComicActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_comic_view);
        //TouchImageView imageView = (TouchImageView)((findViewById(R.id.readComicImage)));
        HorizontalPager pager = (HorizontalPager) ((findViewById(R.id.readComicImage)));
        
        String title = getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        Issue issue = RepositoryFacade.getIssueByTitle(title);
        ImageIterator iterator = new ImageIterator(issue);
        pager.setImageIterator(iterator);
        pager.initialize();
    }
    
}
