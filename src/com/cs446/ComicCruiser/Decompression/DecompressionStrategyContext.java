package com.cs446.ComicCruiser.Decompression;

import java.io.File;

public class DecompressionStrategyContext {
	
	public static DecompressionStrategy getDecompressionStrategy(File f){
		return new ConcreteZIPStrategy(f);
	}

}
