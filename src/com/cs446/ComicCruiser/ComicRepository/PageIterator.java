package com.cs446.ComicCruiser.ComicRepository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PageIterator extends ImageIterator {

	public PageIterator(Issue issue) {
		super(issue);
	}

	@Override
	public Bitmap getNextPage() {
		pageBookmark++;
		return BitmapFactory.decodeStream(strategy.decompressNextImage());
	}

	@Override
	public Bitmap getPreviousPage() {
		// TODO Auto-generated method stub
		pageBookmark--;
		return null;
	}

	@Override
	public void seekToPage(int index) {
		for(int i = 0; i < index; i++){
			strategy.decompressNextImage();
			pageBookmark++;
		}
		pageBookmark-=2;
	}

}
