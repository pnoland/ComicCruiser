package com.cs446.ComicCruiser.FrameRequest;

import com.cs446.ComicCruiser.ComicRepository.Issue;
import java.util.List;

public class FrameRequesterFacade {
	public static List<FrameData> requestFrameBoundaries(Issue issue){
		NetworkWorker nw = new NetworkWorker();
		Boolean result = nw.doInBackground(""); //send the HttpRequest asynchronously
		if(result == true){ //fetching frame data was a success
			return nw.getFD();
		} else { //fetching frame data failed
			return null;
		}
	}
}
