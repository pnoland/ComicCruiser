package com.cs446.ComicCruiser.FrameRequest;

public class FrameData {
	private boolean valid;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	private int x, y, width, height;

	public boolean isValid() {
		return valid;
	}

	public void fetchFramedata() {
		//fetch frame data from server
		//deserialize
		valid = true;
	}

}
