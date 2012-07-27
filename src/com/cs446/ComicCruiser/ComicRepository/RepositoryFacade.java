package com.cs446.ComicCruiser.ComicRepository;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.cs446.ComicCruiser.Activity.ComicCruiserHomeActivity;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.SharedPreferences; 
import android.content.SharedPreferences.Editor;

public class RepositoryFacade {
	
	public static final String PREFERENCE_ISSUE_LIST = "AppIssueList";


	private static final int RECENTLY_READ_ISSUE_LIMIT = 3;


	private static final String PREFERENCE_RECENTLY_READ_LIST = "AppRecentlyReadList";

	
	private static ArrayList<Issue> issueList;
	private static ArrayList<Issue> recentlyRead;
	
	public static Issue getIssueById(String id){
		return null;
	}
	public static Issue getIssueByTitle(String title){
		for(Issue i: issueList){
			if(i.getTitle().equals(title)){
				return i;
			}
		}
		return null;
	}
	public static ArrayList<Issue> getIssueList(){
		return issueList;
	}
	public static void initializeRepository(){
		
		//read up issue objects
		Gson gson = new Gson();
		SharedPreferences settings = ComicCruiserHomeActivity.getInstance().getSharedPreferences("ComicCruiserPreferences", Activity.MODE_PRIVATE); 
		Type issueListType = new TypeToken<ArrayList<Issue>>(){}.getType();
		issueList = gson.fromJson(settings.getString(PREFERENCE_ISSUE_LIST, null), issueListType);
		if(issueList == null){
			issueList = new ArrayList<Issue>();
		}
		Type recentlyReadType = new TypeToken<ArrayList<Issue>>(){}.getType();
		recentlyRead = gson.fromJson(settings.getString(PREFERENCE_RECENTLY_READ_LIST, null), recentlyReadType);
		if(recentlyRead== null){
			recentlyRead = new ArrayList<Issue>(RepositoryFacade.RECENTLY_READ_ISSUE_LIMIT);
		}
	}
	
	public static void persistRepository(){
		Gson gson = new Gson();
		
		String listJson = gson.toJson(issueList);
		String recentJson = gson.toJson(recentlyRead);
		
		SharedPreferences settings = ComicCruiserHomeActivity.getInstance().getSharedPreferences("ComicCruiserPreferences", Activity.MODE_PRIVATE); 
		Editor editor = settings.edit();
		
		editor.putString(PREFERENCE_ISSUE_LIST, listJson);
		editor.putString(PREFERENCE_RECENTLY_READ_LIST, recentJson);
		editor.commit();
	}
	public static void addIssue(String filepath, String title){
		
		//create issue object
		Issue issue = new Issue(filepath, title);
		if(issueList.contains(issue))//disallow duplicates
			return;
		//add to issue list
		issueList.add(issue);
		//issue.initializeImages();
		//persist
		persistRepository();
	}
	public static void batchAdd(List<File> comicFiles) {
    	for(File f : comicFiles) {
    		Issue issue = new Issue(f.getPath(), f.getName());
    		issueList.add(issue);
    	}
    	
    	persistRepository();
	}
	public static ArrayList<String> getRecentIssues() {
		ArrayList<String> values = new ArrayList<String>();
		for(Issue issue : recentlyRead){
			values.add(issue.getTitle());
		}
		return values;
	}
	public static ArrayList<String> getAllIssueTitles() {
		ArrayList<String> values = new ArrayList<String>();
		for(Issue issue : issueList){
			values.add(issue.getTitle());
		}
		return values;
	}
	
	public static ImageIterator openIssueForReading(Issue issue, boolean mode){
		//move to front of queue
		if(!recentlyRead.contains(issue)){
			recentlyRead.add(0, issue);
			if(recentlyRead.size() > RECENTLY_READ_ISSUE_LIMIT){
				recentlyRead.remove(RECENTLY_READ_ISSUE_LIMIT);
			}
		}
		//get image iterator
		ImageIterator iterator;
		if(mode){
			iterator = new FrameIterator(issue);
		}
		else{
			iterator = new PageIterator(issue);
		}
		
		//iterator.seekToPage(issue.getPageBookmark());
		
		return iterator;
	}
	
	public static void closeIssue(ImageIterator iterator){
		//recycle bitmaps
		//set bookmark
		Issue issue = iterator.getIssue();
		issue.setPageBookmark(iterator.getPageBookmark());
	}
	
	public static void deleteIssue(Issue issue){
		issueList.remove(issue);
		recentlyRead.remove(issue);
		persistRepository();
	}
}
