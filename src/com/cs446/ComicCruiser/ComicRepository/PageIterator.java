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
		Bitmap bm = BitmapFactory.decodeStream(strategy.decompressNextImage());
		return bm;
	}

	@Override
	public void seekToIndex(int index) {
		for(int i = 0; i < index; i++){
			strategy.decompressNextImage();
			pageBookmark++;
		}
		pageBookmark-=2;
	}

}
