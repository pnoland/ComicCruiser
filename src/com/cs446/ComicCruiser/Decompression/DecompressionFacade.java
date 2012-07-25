package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class DecompressionFacade {
	
	public static DecompressionStrategy getDecompressionStrategy(String path){
		return DecompressionStrategyContext.getDecompressionStrategy(new File(path));
	}
	
}
