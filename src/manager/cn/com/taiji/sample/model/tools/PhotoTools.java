package cn.com.taiji.sample.model.tools;

import java.io.InputStream;
import java.sql.Blob;

import org.apache.commons.codec.binary.Base64;

public class PhotoTools {

public static String toBase64(Blob photo){
		
		String base64Str = "";
		if(photo != null){
			try {
				InputStream in = photo.getBinaryStream();
				byte[] bytes = new byte[(int) photo.length()];
				in.read(bytes);
				base64Str = Base64.encodeBase64String(bytes);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return base64Str;
	}
}
