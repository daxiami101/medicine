package org.apache.http.examples.client;

import java.awt.Font;
import java.io.File;

public class Word2Pic {
	public static void main(String[] args) throws Exception {
//    	枸杞，白芍，地黄，山药，黄芩，当归，党参，金银花，蒲公英，连翘
		String  medicineName="金银花";
    	int fontSize=30;
    	int fontNum=medicineName.length();
    	FontImage.createImage(medicineName, new Font("微软雅黑", Font.PLAIN, fontSize), 
        		new File("C:/Users/dn/Desktop/二维码测试/1.png"), fontSize*fontNum, 64);
    	
//    	 ZXingCode.getLogoQRCode("https://www.baidu.com/", "");
	}
}
