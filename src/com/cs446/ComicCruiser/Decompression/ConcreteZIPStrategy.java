package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class ConcreteZIPStrategy extends DecompressionStrategy {

	
	public ConcreteZIPStrategy(File compressedFile) {
		super(compressedFile);
	}
	
	FileInputStream fin;
	ZipInputStream zin;

	@Override
	public InputStream decompressNextImage() {
		if(zin == null || fin == null){
			try{
				fin = new FileInputStream(compressedFile);
				zin = new ZipInputStream(fin);
				zin.getNextEntry();
				zin.getNextEntry();
				return zin;
				
			}
			catch(IOException e){
				return null;
			}
		}
		else{
			try{
				zin.getNextEntry();
				return zin;
			}
			catch(IOException e){
				return null;
			}
		}

	}
}
