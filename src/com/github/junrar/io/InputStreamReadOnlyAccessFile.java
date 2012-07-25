package com.github.junrar.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;




/**
 * InputStream based implementation of the <code>IReadOnlyAccess</code> interface.
 * 
 * @see http://rsbweb.nih.gov/ij/
 * @author martinr
 */
public class InputStreamReadOnlyAccessFile implements IReadOnlyAccess {
	private RandomAccessStream is;

	/**
	 * Create new instance.
	 * 
	 * @param is The input stream to wrap.
	 */
	public InputStreamReadOnlyAccessFile(final InputStream is) {
		this.is = new RandomAccessStream(new BufferedInputStream(is));
	}
	
	public long getPosition() throws IOException {
		return is.getLongFilePointer();
	}

	public void setPosition(long pos) throws IOException {
		is.seek(pos);
	}

	public int read() throws IOException {
		return is.read();
	}

	public int read(byte[] buffer, int off, int count) throws IOException {
		return is.read(buffer, off, count);
	}

	public int readFully(byte[] buffer, int count) throws IOException {
		is.readFully(buffer, count);
		return count;
	}

	public void close() throws IOException {
		is.close();
	}

}
