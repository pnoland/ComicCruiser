package com.cs446.ComicCruiser.FrameRequest;

import com.cs446.ComicCruiser.ComicRepository.Issue;

public class FrameRequesterFacade {
	public static void requestFrameBoundaries(Issue issue){
		NetworkWorker nw = new NetworkWorker();
		nw.execute(issue); //send the HttpRequest asynchronously
	}
}
