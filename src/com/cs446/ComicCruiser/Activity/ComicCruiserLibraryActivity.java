package com.cs446.ComicCruiser.Activity;

import java.util.ArrayList;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.ComicIssueListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ComicCruiserLibraryActivity extends Activity {
		public static final String ISSUE_MODE_KEY = "com.cs446.ComicCruiser.issue_mode_key";

		public static String ISSUE_TITLE_KEY = "com.cs446.ComicCruiser.issue_title_key";
		
		private ComicIssueListAdapter issueListAdapter;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.library_view);
	        
	        ListView listView = (ListView) findViewById(R.id.libraryItemsList);
	        
	        ArrayList<String> values = RepositoryFacade.getAllIssueTitles();
	        	
	        issueListAdapter = new ComicIssueListAdapter(this, values);
	        listView.setAdapter(issueListAdapter);
	        TextView searchBarTextView = (TextView) findViewById(R.id.librarySearchView);
			searchBarTextView.addTextChangedListener(new TextWatcher() {
	        	public void onTextChanged(CharSequence s, int start, int before, int count) {
	        	    issueListAdapter.getFilter().filter(s);
	        	}
				public void afterTextChanged(Editable s) {}
				public void beforeTextChanged(CharSequence s, int start,int count, int after) {}});
	    }
	    
	    public void onItemClick(View V) {//handler for click of list item in issue list
	    	//get title
	    	//launch with correct issue object
        	Intent i = new Intent(this, ComicCruiserDetailsActivity.class);
        	i.putExtra(ISSUE_TITLE_KEY, ((TextView)V).getText().toString());
        	startActivity(i);
	    }
	    
	    @Override
	    protected void onResume() {
	    	super.onResume();
	    	ListView listView = (ListView) findViewById(R.id.libraryItemsList);
	    	ArrayList<String> values = RepositoryFacade.getAllIssueTitles();
	        issueListAdapter = new ComicIssueListAdapter(this, values);
	        listView.setAdapter(issueListAdapter);
	    }

}