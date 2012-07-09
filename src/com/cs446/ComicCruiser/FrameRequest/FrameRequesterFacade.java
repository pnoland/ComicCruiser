package com.cs446.ComicCruiser.FrameRequest;

import com.cs446.ComicCruiser.ComicRepository.Issue;


public class FrameRequesterFacade {
	public static FrameData requestFrameBoundaries(Issue issue){
		NetworkWorker nw = new NetworkWorker();
		Boolean result = nw.doInBackground("");
		return null;
	}
}
