package com.cs446.ComicCruiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ComicCruiserInitializationActivity extends Activity {
    View.OnClickListener importClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	ComicCruiserInitializationActivity.this.finish();
        }
      };
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.initialization_view);
	        
	        ListView listView = (ListView) findViewById(R.id.initializationComicsList);
	        
	        String[] values = new String[] { "Y the Last Man Issue 4", "Ex Machina Issue 17", 
	        		"Sandman Volume 2: The Doll's House", "Y the Last Man Issue 3", "Sandman Volume 1: Preludes and Nocturnes", 
	        		"Ex Machina Issue 16", "Ex Machina Issue 15", "V for Vendetta Complete" };
	        
	        FoundItemsAdapter adapter = new FoundItemsAdapter(this, values);
	        listView.setAdapter(adapter);
	      
	        
	        Button b1 = (Button) findViewById(R.id.initializationImportButton);
	        b1.setOnClickListener(importClickHandler);
	    }
	    
	    public void onItemClick(View V) {
	    }

}