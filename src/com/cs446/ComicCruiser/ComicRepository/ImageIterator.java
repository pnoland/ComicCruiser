package com.cs446.ComicCruiser.ComicRepository;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;
import com.cs446.ComicCruiser.Decompression.DecompressionStrategy;

import android.graphics.Bitmap;

public abstract class ImageIterator {
	
	protected Issue issue;
	protected int currentPage = 0;
	protected int currentFame = 0;

	protected DecompressionStrategy strategy;
	
	public ImageIterator(Issue issue){
		this.issue = issue;
		strategy = DecompressionFacade.getNextBitmap(issue.getFilename());
	}
	
	public abstract Bitmap getNextPage();
	
	public abstract Bitmap getPreviousPage();
	
	public abstract Bitmap seekToPage(int index);

}
