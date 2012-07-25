package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import java.io.InputStream;

public abstract class DecompressionStrategy {
	
	protected File compressedFile;
	
	public DecompressionStrategy(File compressedFile){
		this.compressedFile = compressedFile;
	}
	
	public abstract InputStream decompressNextImage();

}
