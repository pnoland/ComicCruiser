package com.cs446.ComicCruiser.ComicRepository;

import java.io.IOException;
import java.util.List;

import com.cs446.ComicCruiser.FrameRequest.FrameData;

import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

public class FrameIterator extends ImageIterator {
	
	int currentPageNum = -1;
	//starts 1 lower than it should so that the increment from initialization doesn't make it off by one
	
	private BitmapRegionDecoder decoder;
	
	private List<FrameData> frameDataList;
	
	public FrameIterator(Issue issue) {
		super(issue);
		frameDataList = issue.getFrameData();
	}

	@Override
	public Bitmap getNextPage() {
		if(frameBookmark > frameDataList.size())
			return null;
		
		FrameData fd = frameDataList.get(frameBookmark++);
		if(decoder == null || fd.getPageNum()> currentPageNum){
			currentPageNum++;
			try{
				decoder = BitmapRegionDecoder.newInstance(strategy.decompressNextImage(), true);
			}
			catch (IOException e) {
				return null;
			}
		}
		
		int left = fd.getX();
		int top = fd.getY();
		int right = fd.getWidth() + left;
		int bottom = fd.getHeight() + top;
		Rect rect = new Rect(left, top, right, bottom);
		return decoder.decodeRegion(rect, null);
	}

	@Override
	public void seekToIndex(int index) {
		int page = 0;
		while(page != index){
			FrameData fd = frameDataList.get(index++);
			page = fd.getPageNum();
		}
		frameBookmark++;
	}

}
