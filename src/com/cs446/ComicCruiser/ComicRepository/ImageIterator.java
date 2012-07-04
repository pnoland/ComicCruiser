package com.cs446.ComicCruiser.ComicRepository;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;

import android.graphics.Bitmap;

public class ImageIterator {
	
	private Issue issue;
	private int currentPage = 0;

	public ImageIterator(Issue issue){
		this.issue = issue;
	}
	
	public Bitmap getNextPage(){
		return DecompressionFacade.getNextBitmap(issue.getFilename());
	}
	
	public Bitmap seekPage(){
		return null;
	}

}
