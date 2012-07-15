package com.cs446.ComicCruiser.ComicRepository;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;
import com.cs446.ComicCruiser.Decompression.DecompressionStrategy;

import android.graphics.Bitmap;

public abstract class ImageIterator {
	
	protected Issue issue;
	public Issue getIssue() {
		return issue;
	}

	protected int pageBookmark = 0;
	protected int frameBookmark = 0;

	protected DecompressionStrategy strategy;
	
	public ImageIterator(Issue issue){
		this.issue = issue;
		strategy = DecompressionFacade.getNextBitmap(issue.getFilepath());
	}
	
	public abstract Bitmap getNextPage();
	
	public abstract Bitmap getPreviousPage();
	
	public abstract void seekToPage(int index);

	public int getPageBookmark() {
		return pageBookmark;
	}

}
