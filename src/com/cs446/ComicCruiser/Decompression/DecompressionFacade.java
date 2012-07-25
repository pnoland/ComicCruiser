package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import android.graphics.Bitmap;

public class DecompressionFacade {
	
	public static DecompressionStrategy getNextBitmap(String path){
		return DecompressionStrategyContext.getDecompressionStrategy(new File(path));
	}
	
	public static Bitmap seekToBitmap(String path, int index){
		return null;
	}
}
