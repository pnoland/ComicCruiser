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
	
	static String currentPath = null;
	static File compressed;
	static FileInputStream fin;
	static ZipInputStream zin;

	public static Bitmap getNextBitmap(String path){
		if(currentPath == null || !path.equals(currentPath)){
			try{
				currentPath = path;
				compressed = new File(path);
				fin = new FileInputStream(compressed);
				zin = new ZipInputStream(fin);
				zin.getNextEntry();
				zin.getNextEntry();
				Bitmap b = BitmapFactory.decodeStream(zin);
				return b;
			} 
			
			catch (IOException e) {
				return null;
			}
		}
		else{	
			try{
				zin.getNextEntry();
				Options o = new Options();
				Bitmap b = BitmapFactory.decodeStream(zin, null, o);
				zin.closeEntry();
				return b;
			}
			catch(IOException e){
				return null;
			}
		}
	}
	
}
