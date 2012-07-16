package com.cs446.ComicCruiser.Activity;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.ComicIssueListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ContextWrapper;
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
        	Intent i = new Intent(ComicCruiserHomeActivity.this, ComicCruiserLibraryActivity.class);
        	startActivity(i);
        }
      };
      View.OnClickListener importClickHandler = new View.OnClickListener() {
          public void onClick(View v) {
          	Intent i = new Intent(ComicCruiserHomeActivity.this, ComicCruiserImportActivity.class);
          	startActivity(i);
          }
        };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        RepositoryFacade.initializeRepository();
        
        setContentView(R.layout.home_view);
        
        ListView listView = (ListView) findViewById(R.id.homeRecentItemsList);
        
        
        ComicIssueListAdapter adapter = new ComicIssueListAdapter(this, RepositoryFacade.getRecentIssues());
        listView.setAdapter(adapter);
        
        Button b1 = (Button) findViewById(R.id.homeImportButton);
        Button b2 = (Button) findViewById(R.id.homeLibraryButton);
        
        b1.setOnClickListener(importClickHandler);
        b2.setOnClickListener(libraryClickHandler);
        
        //Only show Import dialog is the library is empty
        if(RepositoryFacade.getIssueList().isEmpty()) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Import all comics?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        }
    }
    
    public void onItemClick(View V) {
    	//get title
    	//launch with correct issue object
    	Intent i = new Intent(this, ComicCruiserDetailsActivity.class);
    	i.putExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY, ((TextView)V).getText().toString());
    	String t = i.getStringExtra(ComicCruiserLibraryActivity.ISSUE_TITLE_KEY);
    	startActivity(i);
    }
    
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
            case DialogInterface.BUTTON_POSITIVE:
            	Intent i = new Intent(ComicCruiserHomeActivity.this, ComicCruiserInitializationActivity.class);
            	startActivity(i);
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
	}
   
}