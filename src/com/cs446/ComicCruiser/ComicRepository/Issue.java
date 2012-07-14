package com.cs446.ComicCruiser.ComicRepository;

import java.io.File;
import java.util.Vector;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;
import com.cs446.ComicCruiser.Decompression.DecompressionStrategyContext;
import com.cs446.ComicCruiser.FrameRequest.FrameData;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Issue {
	private String filename;
	private String title;
	
	private FrameData frameData;
	
	private int pageBookmark = 0;
	private int frameBookmark = 0;
	
	private int mode = 0;
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	private Issue(){}
	
	public Issue(String filename, String title){
		this.filename = filename;
		this.title = title;
		frameData = new FrameData();
	}
	
	public void getFrameData(){
		frameData.fetchFramedata();
	}
	
	public boolean hasFrameData(){
		return frameData.isValid();
	}

	public String getFilename() {
		return filename;
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
		return BitmapFactory.decodeStream(DecompressionStrategyContext.getDecompressionStrategy(new File(filename)).decompressNextImage());
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
