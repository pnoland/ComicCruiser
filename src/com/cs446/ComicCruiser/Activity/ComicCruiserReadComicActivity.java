package com.cs446.ComicCruiser.Activity;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.ImageIterator;
import com.cs446.ComicCruiser.ComicRepository.Issue;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.HorizontalPager;

import android.app.Activity;

import android.os.Bundle;
import android.widget.Toast;

public class ComicCruiserReadComicActivity extends Activity implements FrameRequestCallback {
    protected static final String ISSUE_TITLE_KEY = "com.cs446.ComicCruiser.issue_title_key";

   Toast toast;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_comic_view);
        //TouchImageView imageView = (TouchImageView)((findViewById(R.id.readComicImage)));
        HorizontalPager pager = (HorizontalPager) ((findViewById(R.id.readComicImage)));
        
        String title = getIntent().getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
        boolean mode = getIntent().getBooleanExtra(ComicCruiserLibraryActivity.ISSUE_MODE_KEY, false);
        Issue issue = RepositoryFacade.getIssueByTitle(title);
        if(mode && !issue.hasFrameData()){
        	issue.fetchFrameData(this);
        	showFetchingAlert();
        }
        else{
	        ImageIterator iterator = RepositoryFacade.openIssueForReading(issue, mode);
	        pager.setImageIterator(iterator);
	        pager.initialize();
        }
    }

	private void showFetchingAlert() {
		CharSequence text = "Fetching Frame Data from the server...";

		toast = Toast.makeText(this, text, 9999999);
		toast.show();
	}

	private void removeFetchingAlert(){
		toast.cancel();
	}
	
	public void reportResults(boolean success) {
		if(success){
			toast.setText("Fetch successful.");
		}
		else{
			toast.setText("Fetch failed. Please try again later.");
		}
		this.runOnUiThread(new Runnable(){
			public void run(){
				try {
					wait(500);
				} catch (InterruptedException e) {
				}
				removeFetchingAlert();
			}
		});
	}
    
}
