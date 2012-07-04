package com.cs446.ComicCruiser;

public class FrameRequester {
	public static Boolean requestFrameBoundaries(String filePath){
		NetworkWorker nw = new NetworkWorker();
		Boolean result = nw.doInBackground(filePath);
		return result;
	}
}
