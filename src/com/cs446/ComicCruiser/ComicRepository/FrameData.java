package com.cs446.ComicCruiser.ComicRepository;

public class FrameData {
	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void fetchFramedata() {
		//fetch frame data from server
		//deserialize
		valid = true;
	}

}
