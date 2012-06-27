package com.cs446.ComicCruiser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ComicCruiserHomeActivity extends Activity {
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
        setContentView(R.layout.home_view);
        
        ListView listView = (ListView) findViewById(R.id.homeRecentItemsList);
        
        String[] values = new String[] { "Y the Last Man Issue 4", "Ex Machina Issue 17", "Sandman Volume 1: Preludes and Nocturnes" };
        
        RecentItemsAdapter adapter = new RecentItemsAdapter(this, values);
        listView.setAdapter(adapter);
        
        Button b1 = (Button) findViewById(R.id.homeImportButton);
        Button b2 = (Button) findViewById(R.id.homeLibraryButton);
        
        b1.setOnClickListener(importClickHandler);
        b2.setOnClickListener(libraryClickHandler);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Import all comics?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show();
    }
    
    public void onItemClick(View V) {
    	Intent i = new Intent(this, ComicCruiserDetailsActivity.class);
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
   
}