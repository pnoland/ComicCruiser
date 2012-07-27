package com.cs446.ComicCruiser.Decompression;

import java.io.File;

public class DecompressionStrategyContext {
	
	public static DecompressionStrategy getDecompressionStrategy(File file){
		String name = file.getName();
		int separatorIndex = name.lastIndexOf(".");
		String ext=name.substring(separatorIndex+1,name.length()); 
		
		if(ext.equals("cbz"))
			return new ConcreteZIPStrategy(file);
		else
			return new ConcreteRARStrategy(file);
	}

}
