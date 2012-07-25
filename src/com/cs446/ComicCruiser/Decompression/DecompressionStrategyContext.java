package com.cs446.ComicCruiser.Decompression;

import java.io.File;

public class DecompressionStrategyContext {
	
	public static DecompressionStrategy getDecompressionStrategy(File f){
		String name = f.getName();
		int mid= name.lastIndexOf(".");
		String ext=name.substring(mid+1,name.length()); 
		if(ext.equals("cbz"))
			return new ConcreteZIPStrategy(f);
		else
			return new ConcreteRARStrategy(f);
	}

}
