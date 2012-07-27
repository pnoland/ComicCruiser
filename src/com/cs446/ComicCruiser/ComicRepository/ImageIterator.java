package com.cs446.ComicCruiser.ComicRepository;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;
import com.cs446.ComicCruiser.Decompression.DecompressionStrategy;

import android.graphics.Bitmap;

public abstract class ImageIterator {
	
	protected Issue issue;
	
	protected int pageBookmark = 0;
	protected int frameBookmark = 0;

	protected DecompressionStrategy strategy;
	
	public ImageIterator(Issue issue){
		this.issue = issue;
		strategy = DecompressionFacade.getDecompressionStrategy(issue.getFilepath());
	}
	
	public abstract Bitmap getNextPage();
	
	public abstract void seekToIndex(int index);
	
	public Issue getIssue() {
		return issue;
	}

	public int getPageBookmark() {
		return pageBookmark;
	}

	public int getFrameBookmark() {
		return frameBookmark;
	}

}
