package com.cs446.ComicCruiser.ComicRepository;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import com.cs446.ComicCruiser.View.FoundItemsAdapter;

public class FileSystemWorker {
	
	public static ArrayList<File> findExistingIssues(FoundItemsAdapter adapter){
		FileFilter issueFilter = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().contains(".cbz") || f.getName().contains(".cbr");
			}
		};
		
		//TO DO: Ensure that the directory is always called sdcard on every Android device
		return listIssueFiles(new File("/sdcard/"), issueFilter, true, adapter);
	}
	
	public static ArrayList<File> listIssueFiles(File rootDir, FileFilter filter, boolean recursive, FoundItemsAdapter adapter) {
	    ArrayList<File> result = new ArrayList<File>();
	    if( !rootDir.exists() || !rootDir.isDirectory() ) 
	        return result;


	    //Add all files that comply with the given filter
	    File[] files = rootDir.listFiles(filter);
	    for( File f : files) {
	        if( !result.contains(f) ){
	            result.add(f);
	            adapter.reportFoundItems(result);
	        }
	    }

	    //Recurse through all available dirs if we are scanning recursively
	    if( recursive ) {
	        File[] dirs = rootDir.listFiles(new DirFilter());
	        for( File f : dirs ) {
	            if( f.canRead() ) {
	                result.addAll(listIssueFiles(f, filter, recursive, adapter));
	            }
	        }
	    }

	    return result;
	}
	
	private static class DirFilter implements FileFilter {
		
	    public boolean accept(File pathname) {
	        if( pathname.isDirectory() ) 
	            return true;

	        return false;
	    }

	}

}
