package com.cs446.ComicCruiser.ComicRepository;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class FileSystemWorker {
	
	public static ArrayList<File> findExistingIssues(){
		FileFilter issueFilter = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().contains(".cbz") || f.getName().contains(".cbr");
			}
		};
		
		return listIssueFiles(new File("/"), issueFilter, true);
	}
	
	public static ArrayList<File> listIssueFiles(File rootDir, FileFilter filter, boolean recursive) {
	    ArrayList<File> result = new ArrayList<File>();
	    if( !rootDir.exists() || !rootDir.isDirectory() ) 
	        return result;


	    //Add all files that comply with the given filter
	    File[] files = rootDir.listFiles(filter);
	    for( File f : files) {
	        if( !result.contains(f) )
	            result.add(f);
	    }

	    //Recurse through all available dirs if we are scanning recursively
	    if( recursive ) {
	        File[] dirs = rootDir.listFiles(new DirFilter());
	        for( File f : dirs ) {
	            if( f.canRead() ) {
	                result.addAll(listIssueFiles(f, filter, recursive));
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
