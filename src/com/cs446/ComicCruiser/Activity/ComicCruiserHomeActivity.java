package com.cs446.ComicCruiser.Activity;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.ComicIssueListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ComicCruiserHomeActivity extends Activity {
	private static Activity instance;
    View.OnClickListener libraryClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(ComicCruiserHomeActivity.this, ComicCruiserLibraryActivity.class);
        	startActivity(intent);
        }
      };
      View.OnClickListener importClickHandler = new View.OnClickListener() {
          public void onClick(View v) {
          	Intent intent = new Intent(ComicCruiserHomeActivity.this, ComicCruiserImportActivity.class);
          	startActivity(intent);
          }
        };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        RepositoryFacade.initializeRepository();
        setContentView(R.layout.home_view);
        
        ListView listView = (ListView) findViewById(R.id.homeRecentItemsList);
        ComicIssueListAdapter recentItemsAdapter = new ComicIssueListAdapter(this, RepositoryFacade.getRecentIssues());
        listView.setAdapter(recentItemsAdapter);
        
        Button importButton = (Button) findViewById(R.id.homeImportButton);
        Button libraryButton = (Button) findViewById(R.id.homeLibraryButton);
        
        importButton.setOnClickListener(importClickHandler);
        libraryButton.setOnClickListener(libraryClickHandler);
        
        //Only show Import dialog is the library is empty
        if(RepositoryFacade.getIssueList().isEmpty()) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Import all comics?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        }
        
        //Only show Recent Items label if there are recent items.
        toggleRecentItemsLabel();
    }
    
    private void toggleRecentItemsLabel(){
    	if(RepositoryFacade.getRecentIssues().isEmpty()){
        	TextView tv = (TextView) findViewById(R.id.homeRecentItemsLabel);
	    	tv.setVisibility(View.GONE);
        } else {
        	TextView tv = (TextView) findViewById(R.id.homeRecentItemsLabel);
	    	tv.setVisibility(View.VISIBLE);
        }
    }
    
    public void onItemClick(View V) {//handler for click of list item in recent issues
    	//get title
    	//launch with correct issue object
    	Intent intent = new Intent(this, ComicCruiserDetailsActivity.class);
    	intent.putExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY, ((TextView)V).getText().toString());
    	startActivity(intent);
    }
    
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
            case DialogInterface.BUTTON_POSITIVE:
            	Intent intent = new Intent(ComicCruiserHomeActivity.this, ComicCruiserInitializationActivity.class);
            	startActivity(intent);
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
            }
        }
    };
	public static Activity getInstance() {
		return instance;
	}
	
	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		RepositoryFacade.persistRepository();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ListView listView = (ListView) findViewById(R.id.homeRecentItemsList);
        ComicIssueListAdapter adapter = new ComicIssueListAdapter(this, RepositoryFacade.getRecentIssues());
        listView.setAdapter(adapter);
        //Only show Recent Items label if there are recent items.
        toggleRecentItemsLabel();
	}
   
}