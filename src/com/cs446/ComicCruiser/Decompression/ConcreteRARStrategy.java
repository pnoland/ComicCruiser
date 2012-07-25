package com.cs446.ComicCruiser.Decompression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

public class ConcreteRARStrategy extends DecompressionStrategy {
	private Archive arch;
	public ConcreteRARStrategy(File compressedFile) {
		super(compressedFile);
		try {
			arch = new Archive(compressedFile);
			arch.nextFileHeader();
		} catch (RarException e) {} catch (IOException e) {}
		
	}

	@Override
	public InputStream decompressNextImage() {
		FileHeader fh = arch.nextFileHeader();
		ByteArrayOutputStream os =  new ByteArrayOutputStream();
		try {
			arch.extractFile(fh, os);
		} catch (RarException e) {
			return null;
		}
		byte[] bytes = os.toByteArray();
		InputStream is = new ByteArrayInputStream(bytes);
		return is;
	}

}
