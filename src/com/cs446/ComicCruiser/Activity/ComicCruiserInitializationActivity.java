package com.cs446.ComicCruiser.Activity;

import java.io.File;
import java.util.ArrayList;
import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.FoundItemsAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ComicCruiserInitializationActivity extends Activity {
    View.OnClickListener importClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	RepositoryFacade.batchAdd(adapter.getValues());
        	
        	ComicCruiserInitializationActivity.this.finish();
        }
      };
      private FoundItemsAdapter adapter;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.initialization_view);
	        
	        ListView listView = (ListView) findViewById(R.id.initializationComicsList);
	        
	        adapter = new FoundItemsAdapter(this, new ArrayList<File>(), this);
	        listView.setAdapter(adapter);
	        
	        Button importButton = (Button) findViewById(R.id.initializationImportButton);
	        importButton.setOnClickListener(importClickHandler);
	    }
	   
	    public void showImportButton(){
	    	Button importButton = (Button) findViewById(R.id.initializationImportButton);
	    	importButton.setVisibility(View.VISIBLE);
	    	ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
	    	pb.setVisibility(View.GONE);
	    	TextView tv = (TextView) findViewById(R.id.findingComicsLabel);
	    	tv.setVisibility(View.GONE);
	    }
}