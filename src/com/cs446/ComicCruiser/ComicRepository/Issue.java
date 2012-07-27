package com.cs446.ComicCruiser.ComicRepository;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import com.cs446.ComicCruiser.Activity.FrameRequestCallback;
import com.cs446.ComicCruiser.Decompression.DecompressionStrategyContext;
import com.cs446.ComicCruiser.FrameRequest.FrameData;
import com.cs446.ComicCruiser.FrameRequest.FrameRequesterFacade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Issue {
	private String filepath;
	private String title;
	
	private List<FrameData> frameData;
	
	private int pageBookmark = 0;
	private int frameBookmark = 0;
	
	private int mode = 0;
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public Issue(String filepath, String title){
		this.filepath = filepath;
		this.title = title;
		frameData = new ArrayList<FrameData>();
	}
	
	public void fetchFrameData(FrameRequestCallback callback){
		FrameRequesterFacade.requestFrameBoundaries(this, callback);
	}
	
	public void setFrameData(List<FrameData> frameData){
		this.frameData = frameData;
	}
	
	public boolean hasFrameData(){
		return !frameData.isEmpty();
	}
	
	public List<FrameData> getFrameData(){
		return frameData;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getTitle() {
		return title;
	}

	public int getPageBookmark() {
		return pageBookmark;
	}

	public void setPageBookmark(int pageBookmark) {
		this.pageBookmark = pageBookmark;
	}

	public int getFrameBookmark() {
		return frameBookmark;
	}

	public void setFrameBookmark(int frameBookmark) {
		this.frameBookmark = frameBookmark;
	}
	
	public Bitmap getCoverPage(){
		return BitmapFactory.decodeStream(DecompressionStrategyContext.getDecompressionStrategy(new File(filepath)).decompressNextImage());
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Issue)){
			return false;
		}
		Issue i = (Issue) o;
		return i.getTitle().equals(this.title);
	}

}
