package com.cs446.ComicCruiser.Decompression;

import java.io.File;
import java.io.InputStream;

public class ConcreteRARStrategy extends DecompressionStrategy {

	public ConcreteRARStrategy(File compressedFile) {
		super(compressedFile);
	}

	@Override
	public InputStream decompressNextImage() {
		return null;
	}

}
