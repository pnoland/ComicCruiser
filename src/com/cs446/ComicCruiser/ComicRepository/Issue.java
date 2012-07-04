package com.cs446.ComicCruiser.ComicRepository;

import java.util.Vector;

import com.cs446.ComicCruiser.Decompression.DecompressionFacade;

import android.graphics.Bitmap;

public class Issue {
	private String filename;
	private String title;
	
	private Vector<Bitmap> images;
	private FrameData frameData;
	
	private Issue(){}
	
	public Issue(String filename, String title){
		this.filename = filename;
		this.title = title;
		images = new Vector<Bitmap>();
		frameData = new FrameData();
	}
	
	public void initializeImages(){
		//read up file and decompress
		Bitmap b = DecompressionFacade.getNextBitmap(filename);
		images.add(b);
		//iterate through jpeg data, decode and add to images vector
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

}
