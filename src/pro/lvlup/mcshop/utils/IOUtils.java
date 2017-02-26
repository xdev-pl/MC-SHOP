package pro.lvlup.mcshop.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeoutException;

public class IOUtils {

	public static String getContent(String s) {
		String body = null;
		try {
			URL url = new URL(s);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = toString(in, encoding);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return body;
	}

	public static String toString(InputStream in, String en) throws Exception {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		byte[] buf = new byte['?'];
		int length = 0;
		while ((length = in.read(buf)) != -1) {
			b.write(buf, 0, length);
		}
		return new String(b.toByteArray(), en);
	}
}
