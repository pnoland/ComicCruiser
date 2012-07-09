package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public abstract class DecompressionStrategy {
	
	protected File compressedFile;
	
	public DecompressionStrategy(File compressedFile){
		this.compressedFile = compressedFile;
	}
	
	public abstract InputStream decompressNextImage();

}
