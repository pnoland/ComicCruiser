package com.cs446.ComicCruiser.FrameRequest;

import com.cs446.ComicCruiser.Activity.FrameRequestCallback;
import com.cs446.ComicCruiser.ComicRepository.Issue;

public class FrameRequesterFacade {
	public static void requestFrameBoundaries(Issue issue, FrameRequestCallback callback){
		NetworkWorker nw = new NetworkWorker();
		nw.execute(issue, callback); //send the HttpRequest asynchronously
	}
}
