package com.cs446.ComicCruiser.ComicRepository;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

import com.cs446.ComicCruiser.Activity.ComicCruiserHomeActivity;
import com.google.gson.*;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.SharedPreferences; 
import android.content.SharedPreferences.Editor;

public class RepositoryFacade {
	
	public static final String PREFERENCE_ISSUE_LIST = "AppIssueList";

	
	static Vector<Issue> issueList = new Vector<Issue>();
	
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
	public static Vector<Issue> getIssueList(){
		return issueList;
	}
	public static Vector<Issue> getFilteredIssueList(String filterString){
		return null;
	}
	@SuppressWarnings("unchecked")
	public static void initializeRepository(){
		issueList = new Vector<Issue>();
		
		//read up issue objects
		Gson gson = new Gson();
		SharedPreferences settings = ComicCruiserHomeActivity.getInstance().getSharedPreferences("ComicCruiserPreferences", Activity.MODE_PRIVATE); 
		Type issueListType = new TypeToken<Vector<Issue>>(){}.getType();
		issueList = gson.fromJson(settings.getString(PREFERENCE_ISSUE_LIST, null), issueListType);
		//need to check to make sure issueList does not get set to null
		if(issueList == null){
			issueList = new Vector<Issue>();
		}
	}
	
	public static void persistRepository(){
		Gson gson = new Gson();
		String json = gson.toJson(issueList);
		SharedPreferences settings = ComicCruiserHomeActivity.getInstance().getSharedPreferences("ComicCruiserPreferences", Activity.MODE_PRIVATE); 
		Editor editor = settings.edit();
		editor.putString(PREFERENCE_ISSUE_LIST, json);
		editor.commit();
	}
	public static void addIssue(String filename, String title){
		//create issue object
		Issue issue = new Issue(filename, title);
		//add to issue list
		issueList.add(issue);
		//issue.initializeImages();
		//persist
		persistRepository();
	}
	public static ArrayList<String> getRecentIssues() {
		//TODO: limit number and make them most recently read issues
		ArrayList<String> values = new ArrayList<String>();
		for(Issue issue : issueList){
			values.add(issue.getTitle());
		}
		return values;
	}
	public static ArrayList<String> getAllIssueTitles() {
		//TODO: limit number and make them most recently read issues
		ArrayList<String> values = new ArrayList<String>();
		for(Issue issue : issueList){
			values.add(issue.getTitle());
		}
		return values;
	}
	
	public static ImageIterator openIssueForReading(Issue issue){
		//move to front of queue
		//get image iterator
		return new PageIterator(issue);
	}
	
	public static void closeIssue(Issue issue){
		//recycle bitmaps
	}
	
}
