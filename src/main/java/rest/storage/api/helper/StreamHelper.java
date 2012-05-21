package rest.storage.api.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileWriteChannel;

public class StreamHelper {

	public static byte[] getBytes(FileReadChannel channel) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] barray = new byte[1024];
		ByteBuffer bb = ByteBuffer.wrap(barray);
		int nRead;
		while ((nRead=channel.read(bb)) != -1) {
			for (int i=0; i < nRead; i++) {
				out.write(barray[i]);
			}
			bb.clear();
		}
		
		return out.toByteArray();
	}
	
	public static long writeToChannel(InputStream input, FileWriteChannel channel) throws IOException {
		int read = 0;
		long length = 0;
		byte[] bytes = new byte[1024];
	 
		while ((read = input.read(bytes)) != -1) {
			//out.write(bytes, 0, read);
			length += read;
			channel.write(ByteBuffer.wrap(bytes));
		}
		
		return length;
	}
}
