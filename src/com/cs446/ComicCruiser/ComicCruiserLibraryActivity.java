package com.cs446.ComicCruiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class ComicCruiserLibraryActivity extends Activity {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.library_view);
	        
	        ListView listView = (ListView) findViewById(R.id.libraryItemsList);
	        
	        String[] values = new String[] { "Y the Last Man Issue 4", "Ex Machina Issue 17", 
	        		"Sandman Volume 2: The Doll's House", "Y the Last Man Issue 3", "Sandman Volume 1: Preludes and Nocturnes", 
	        		"Ex Machina Issue 16", "Ex Machina Issue 15", "V for Vendetta Complete" };
	        
	        RecentItemsAdapter adapter = new RecentItemsAdapter(this, values);
	        listView.setAdapter(adapter);
	        ((EditText)findViewById(R.id.librarySearchView)).setText("Filter");
	    }
	    
	    public void onItemClick(View V) {
        	Intent i = new Intent(this, ComicCruiserDetailsActivity.class);
        	startActivity(i);
	    }

}