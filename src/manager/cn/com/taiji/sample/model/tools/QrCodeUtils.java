package cn.com.taiji.sample.model.tools;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrCodeUtils {

	public static File generatePic(String id) throws WriterException, IOException{
		 String text = "http://219.150.166.85:8081/sample/app/common/info/"+id;//二维码的内容
//		 				http://219.150.166.85:8081/sample/app/common/welcome  
		 int width = 400;  
	        int height = 400;  
	        String format = "png";    
	        Hashtable<EncodeHintType,String> hints= new Hashtable<>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
	        File outputFile = new File("C:/Users/dn/Desktop/二维码测试/001code.png"); 
	        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile); 
	        System.out.println("It is ok!!!");
	        return outputFile;
	        
	}
	public static void main(String[] args) throws WriterException, IOException {
		String id="7f0d5e08c8024de2a4d34d8c3a58d438";
		generatePic(id);
	}
}
