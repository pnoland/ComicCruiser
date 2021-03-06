package com.cs446.ComicCruiser.View;

import java.io.File;
import java.util.ArrayList;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.Activity.ComicCruiserInitializationActivity;
import com.cs446.ComicCruiser.ComicRepository.FileSystemWorker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//Adapter used to update the view when performing library initialization
public class FoundItemsAdapter extends ArrayAdapter<File> {
	private final Context context;
	private ArrayList<File> values;
	private ComicCruiserInitializationActivity activity;

	public FoundItemsAdapter(Context context, ArrayList<File> values, ComicCruiserInitializationActivity activity) {
		super(context, R.layout.found_items_row_layout, values);
		this.context = context;
		this.values = values;
		this.activity = activity;
		
		//Search the file system asynchronously so the view can display files as they are found
		new Thread(new Runnable() {
			public void run() {
				listFoundItems();
		    }
		}).start();
	}
	
	public ArrayList<File> getValues() {
		return values;
	}
	
	public void reportFoundItems(final ArrayList<File> items){
		//Need the UI thread to update the view
		activity.runOnUiThread(new Runnable(){
			public void run(){
				values.clear();
				values.addAll(items);
				FoundItemsAdapter.this.notifyDataSetChanged();
			}
		});
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.initialization_items_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values.get(position).getPath() + "\n");//Concatenated a \n so the bottom line doesn't get cut off
		return rowView;
	}
	
	private void listFoundItems() {
		ArrayList<File> allItems = FileSystemWorker.findExistingIssues(this);
		reportFoundItems(allItems);
		activity.runOnUiThread(new Runnable(){
			public void run(){
				activity.showImportButton();
			}
		});
	}
}