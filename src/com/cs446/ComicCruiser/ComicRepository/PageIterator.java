package com.cs446.ComicCruiser.ComicRepository;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PageIterator extends ImageIterator {

	public PageIterator(Issue issue) {
		super(issue);
	}

	@Override
	public Bitmap getNextPage() {
		return BitmapFactory.decodeStream(strategy.decompressNextImage());
	}

	@Override
	public Bitmap getPreviousPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bitmap seekToPage(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
