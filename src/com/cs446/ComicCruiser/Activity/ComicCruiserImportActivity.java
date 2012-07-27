package com.cs446.ComicCruiser.Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.RepositoryFacade;
import com.cs446.ComicCruiser.View.FileArrayAdapter;
import com.cs446.ComicCruiser.View.Option;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class ComicCruiserImportActivity extends ListActivity {
    private File currentDir;
    private FileArrayAdapter adapter;
	private String currentPath;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File(Environment.getExternalStorageDirectory().getPath());
        fill(currentDir);
    }
    private void fill(File f){
    	File[] allFiles = f.listFiles();
    	this.setTitle("Current Directory: "+f.getName());
    	List<Option>directories = new ArrayList<Option>();
    	List<Option>files = new ArrayList<Option>();
		 try{
			 for(File file: allFiles)
			 {
				if(file.isDirectory())
					directories.add(new Option(file.getName(),"Folder",file.getAbsolutePath()));
				else
				{
					String name = file.getName();
					int mid= name.lastIndexOf(".");
					String ext=name.substring(mid+1,name.length()); 
					if(ext.equals("cbz")|| ext.equals("cbr"))
						files.add(new Option(file.getName(),"Size: "+file.length(), file.getAbsolutePath()));
				}
			 }
		 }catch(Exception e)
		 {
			 
		 }
		 Collections.sort(directories);
		 Collections.sort(files);
		 directories.addAll(files);
		 if(!f.getName().equalsIgnoreCase("sdcard")){
			 directories.add(0,new Option("..","Parent Directory",f.getParent()));
		 }
		 
		 adapter = new FileArrayAdapter(ComicCruiserImportActivity.this,R.layout.file_view,directories);
		 this.setListAdapter(adapter);
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else
		{
			onFileClick(o);
		}
	}
    private void onFileClick(Option o)
    {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("Enter a title");
    	alert.setMessage("Enter a title for this issue:");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	input.setText(o.getName());
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  Editable value = input.getText();
    	  ComicCruiserImportActivity.this.addComicWithTitle(value.toString());
    	  }
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	currentPath = o.getPath();
    	alert.show();
    }
	    
	    public void addComicWithTitle(String title) {
	    	RepositoryFacade.addIssue(currentPath, title);
	    	finish();
	    }
		public void onItemClick(View V) {
	    }

}