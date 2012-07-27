package com.cs446.ComicCruiser.Decompression;

import java.io.File;

public class DecompressionFacade {
	
	public static DecompressionStrategy getDecompressionStrategy(String path){
		return DecompressionStrategyContext.getDecompressionStrategy(new File(path));
	}
	
}
